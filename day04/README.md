# 도커실습

### 도커 개발환경 설정

##### 도커 설치

Window 10 home 은 Hyper-V(가상화 SW)가 없으므로 Docker toolbox를 설치했다.

##### 도커 버전 확인

```shell
$ docker -v
Docker version 19.03.1, build 74b1e89e8a
```

##### 테스트용 Hello world 도커 컨테이너 실행

```shell
$ docker run hello-world
Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
1b930d010525: Pull complete                                                                                             Digest: sha256:f9dfddf63636d84ef479d645ab5885156ae030f611a56f3a7ac7f2fdd86d7e4e
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
```

### 기본 명령어 실습

##### 컨테이너 조회

```shell
$ docker ps -a
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                     PORTS               NAMES
bbef4ccb8cac        hello-world         "/hello"            6 minutes ago       Exited (0) 6 minutes ago                       nice_visvesvaraya
```

hello world 하나밖에 없다.

##### hello world 컨테이너 삭제

```shell
$ docker rm bbef4ccb8cac                                               bbef4ccb8cac
```

##### 도커 이미지 조회

```shell
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
hello-world         latest              fce289e99eb9        14 months ago       1.84kB
```

##### hello world 도커 이미지 삭제

```shell
$ docker rmi hello-world:latest
Untagged: hello-world:latest
Untagged: hello-world@sha256:f9dfddf63636d84ef479d645ab5885156ae030f611a56f3a7ac7f2fdd86d7e4e
Deleted: sha256:fce289e99eb9bca977dae136fbe2a82b6b7d4c372474c9235adc1741675f587e
Deleted: sha256:af0b15c8625bb1938f1d7b17081031f649fd14e6b233688eea3c5483994a66a3
```

### Jenkins를 이용한 도커 실습

##### Jenkins를 도커 컨테이너로 실행 및 실행(Up STATUS)중인지 확인

```shell
$ docker run --name myjenkins -d -p 8080:8080 jenkins:260.3
Unable to find image 'jenkins:2.60.3' locally
2.60.3: Pulling from library/jenkins
55cbf04beb70: Pull complete                                                               1607093a898c: Pull complete                                                               9a8ea045c926: Pull complete                                                               d4eee24d4dac: Pull complete                                                               c58988e753d7: Pull complete                                                               70fcfa476f73: Pull complete                                                               0539c80a02be: Pull complete                                                         54fefc6dcf80: Pull complete                                                               911bc90e47a8: Pull complete                                                               38430d93efed: Pull complete                                                               7e46ccda148a: Pull complete                                                               c0cbcb5ac747: Pull complete
35ade7a86a8e: Pull complete
aa433a6a56b1: Pull complete
841c1dd38d62: Pull complete
b865dcb08714: Pull complete
5a3779030005: Pull complete
12b47c68955c: Pull complete
1322ea3e7bfd: Pull complete
Digest: sha256:eeb4850eb65f2d92500e421b430ed1ec58a7ac909e91f518926e02473904f668
Status: Downloaded newer image for jenkins:2.60.3
92e0a136909aff1e04c73f61e3489dc3547ab2fe5dd6daebe5f62e718656bde2
```

- \-d
  - 백그라운드 데몬으로 실행
- -p
  - 가상 머신(Guest PC)에서 동작하느 ㄴ서비스에 docker proxy 서비스가 NAT 기능을 수행해 포트 포워딩을 해주어 실제 머신(HOST PC) IP로 서비스에 접속이 가능하도록 해준다.
- 컨테이너가 삭제되면 생성된 데이터가 같이 삭제되므로 보통 호스트 PC의 디렉토리에 데이터가 생성되도록 컨테이너에는 볼륨 마운트 옵션(-v)을 추가하여 실행시킨다.
  - ex) -v /your/home:/var/jenkins_home

```shell
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED              STATUS              PORTS                               NAMES
92e0a136909a        jenkins:2.60.3      "/bin/tini -- /usr/l…"   About a minute ago   Up About a minute   0.0.0.0:8080->8080/tcp, 50000/tcp   myjenkins
```

##### Jenkins 서버 컨테이너의 bash 실행 후 컨테이너의 OS 버전 확인

```shell
$ docker exec -it myjenkins bash
jenkins@92e0a136909a:/$ cat /etc/issue
Debian GNU/Linux 9 \n \l
```

- jekins 컨테이너는 bash가 포함되어 있지만 없는 서비스의 경우에는 bash 대신 sh를 사용해도 된다.
- 윈도우 git-bash에서 "the input device is not a TTY." 에러가 발생하면 cmd 창 이용하자.
- 컨테이너의 bash가 실행된 상태는 마치 ssh로 리눅스 서버에 접속해서 명령어를 사용하는 것과 비슷하다.
- -it 옵션은 -i: interactive, -t: tty 가 합쳐진 의미로 터미널에서 입출력이 가능해진다.

##### 컨테이너 안(bash)에서 Admin 패스워드가 저장된 파일 확인 후 컨테이너 bash 종료

```shell
jenkins@92e0a136909a:/$ ls
bin   dev               etc   lib    media  opt   root  sbin  sys  usr
boot  docker-java-home  home  lib64  mnt    proc  run   srv   tmp  var
jenkins@92e0a136909a:/$ cat /var/jenkins_home/secrets/initialAdminPassword
[password]
jenkins@92e0a136909a:/$ exit
exit
```

##### 2-3번의 과정을 한줄의 명령어로 한번에 실행하기

```shell
$ docker exec myjenkins cat /var/jenkins_home/secrets/initialAdminPassword
0e37bc22243a4f39bd45dd00efd33aaf
```

- docker 명령어는 개발 PC(Host PC)에서 실행하는 명령어 이므로 컨테이너 안쪽(bash)에서 실행하는 방식과 비교를 위해 설명한 것이므로 실행 위치를 혼동되지 않도록 실습해야 한다.

- docker logs myjenkins 명령어를 실행해서 컨테이너 실행 시 출력된 메시지에서도 패스워드 확인이 가능하다.

  ```shell
  $ docker logs myjenkins
  Running from: /usr/share/jenkins/jenkins.war
  webroot: EnvVars.masterEnvVars.get("JENKINS_HOME")
  Mar 12, 2020 6:06:04 AM Main deleteWinstoneTempContents
  WARNING: Failed to delete the temporary Winstone file /tmp/winstone/jenkins.war
  Mar 12, 2020 6:06:04 AM org.eclipse.jetty.util.log.JavaUtilLog info
  INFO: Logging initialized @782ms
  Mar 12, 2020 6:06:04 AM winstone.Logger logInternal
  INFO: Beginning extraction from war file
  Mar 12, 2020 6:06:10 AM org.eclipse.jetty.util.log.JavaUtilLog warn
  WARNING: Empty contextPath
  Mar 12, 2020 6:06:10 AM org.eclipse.jetty.util.log.JavaUtilLog info
  INFO: jetty-9.2.z-SNAPSHOT
  Mar 12, 2020 6:06:11 AM org.eclipse.jetty.util.log.JavaUtilLog info
  INFO: NO JSP Support for /, did not find org.eclipse.jetty.jsp.JettyJspServlet
  Jenkins home directory: /var/jenkins_home found at: EnvVars.masterEnvVars.get("JENKINS_HOME")
  Mar 12, 2020 6:06:12 AM org.eclipse.jetty.util.log.JavaUtilLog info
  INFO: Started w.@47404bea{/,file:/var/jenkins_home/war/,AVAILABLE}{/var/jenkins_home/war}
  Mar 12, 2020 6:06:12 AM org.eclipse.jetty.util.log.JavaUtilLog info
  INFO: Started ServerConnector@1c80e49b{HTTP/1.1}{0.0.0.0:8080}
  Mar 12, 2020 6:06:12 AM org.eclipse.jetty.util.log.JavaUtilLog info
  INFO: Started @8823ms
  Mar 12, 2020 6:06:12 AM winstone.Logger logInternal
  INFO: Winstone Servlet Engine v2.0 running: controlPort=disabled
  Mar 12, 2020 6:06:13 AM jenkins.InitReactorRunner$1 onAttained
  INFO: Started initialization
  Mar 12, 2020 6:06:13 AM jenkins.InitReactorRunner$1 onAttained
  INFO: Listed all plugins
  Mar 12, 2020 6:06:15 AM jenkins.InitReactorRunner$1 onAttained
  INFO: Prepared all plugins
  Mar 12, 2020 6:06:15 AM jenkins.InitReactorRunner$1 onAttained
  INFO: Started all plugins
  Mar 12, 2020 6:06:15 AM jenkins.InitReactorRunner$1 onAttained
  INFO: Augmented all extensions
  Mar 12, 2020 6:06:15 AM jenkins.InitReactorRunner$1 onAttained
  INFO: Loaded all jobs
  Mar 12, 2020 6:06:17 AM hudson.model.AsyncPeriodicWork$1 run
  INFO: Started Download metadata
  Mar 12, 2020 6:06:18 AM jenkins.util.groovy.GroovyHookScript execute
  INFO: Executing /var/jenkins_home/init.groovy.d/tcp-slave-agent-port.groovy
  Mar 12, 2020 6:06:18 AM jenkins.InitReactorRunner$1 onAttained
  INFO: Completed initialization
  Mar 12, 2020 6:06:19 AM org.springframework.context.support.AbstractApplicationContext prepareRefresh
  INFO: Refreshing org.springframework.web.context.support.StaticWebApplicationContext@1484ddb: display name [Root WebApplicationContext]; startup date [Thu Mar 12 06:06:19 UTC 2020]; root of context hierarchy
  Mar 12, 2020 6:06:19 AM org.springframework.context.support.AbstractApplicationContext obtainFreshBeanFactory
  INFO: Bean factory for application context [org.springframework.web.context.support.StaticWebApplicationContext@1484ddb]: org.springframework.beans.factory.support.DefaultListableBeanFactory@313dc679
  Mar 12, 2020 6:06:19 AM org.springframework.beans.factory.support.DefaultListableBeanFactory preInstantiateSingletons
  INFO: Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@313dc679: defining beans [authenticationManager]; root of factory hierarchy
  Mar 12, 2020 6:06:19 AM org.springframework.context.support.AbstractApplicationContext prepareRefresh
  INFO: Refreshing org.springframework.web.context.support.StaticWebApplicationContext@727322b0: display name [Root WebApplicationContext]; startup date [Thu Mar 12 06:06:19 UTC 2020]; root of context hierarchy
  Mar 12, 2020 6:06:19 AM org.springframework.context.support.AbstractApplicationContext obtainFreshBeanFactory
  INFO: Bean factory for application context [org.springframework.web.context.support.StaticWebApplicationContext@727322b0]: org.springframework.beans.factory.support.DefaultListableBeanFactory@32d666c4
  Mar 12, 2020 6:06:19 AM org.springframework.beans.factory.support.DefaultListableBeanFactory preInstantiateSingletons
  INFO: Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@32d666c4: defining beans [filter,legacy]; root of factory hierarchy
  Mar 12, 2020 6:06:20 AM jenkins.install.SetupWizard init
  INFO:
  
  *************************************************************
  *************************************************************
  *************************************************************
  
  Jenkins initial setup is required. An admin user has been created and a password generated.
  Please use the following password to proceed to installation:
  
  [password]
  
  This may also be found at: /var/jenkins_home/secrets/initialAdminPassword
  
  *************************************************************
  *************************************************************
  *************************************************************
  
  --> setting agent port for jnlp
  --> setting agent port for jnlp... done
  Mar 12, 2020 6:06:42 AM hudson.model.UpdateSite updateData
  INFO: Obtained the latest update center data file for UpdateSource default
  Mar 12, 2020 6:06:42 AM hudson.model.UpdateSite updateData
  INFO: Obtained the latest update center data file for UpdateSource default
  Mar 12, 2020 6:06:42 AM hudson.WebAppMain$3 run
  INFO: Jenkins is fully up and running
  Mar 12, 2020 6:06:44 AM hudson.model.DownloadService$Downloadable load
  INFO: Obtained the updated data file for hudson.tasks.Maven.MavenInstaller
  Mar 12, 2020 6:06:48 AM hudson.model.DownloadService$Downloadable load
  INFO: Obtained the updated data file for hudson.tools.JDKInstaller
  Mar 12, 2020 6:06:48 AM hudson.model.AsyncPeriodicWork$1 run
  INFO: Finished Download metadata. 30,820 ms
  ```

##### 컨테이너 안에 있는 패스워드 파일을 개발 PC로 복사하기

```shell
$ docker cp myjenkins:/var/jenkins_home/secrets/initialAdminPassword ./
open C:\Program Files\Docker Toolbox\initialAdminPassword: Access is denied.
```

- 접근이 거부 당했다.

##### 웹 브라우져에서 접속(http://localhost:8080) 해서 앞서 확인 한 패스워드를 붙여 넣고 Jenkins 설정을 계속 진행하기

- container가 실행중인데 접속이 안된다.

### Skeleton 코드 기반 도커 이미지 제작(프론트)

##### 공통 웹/모바일 트랙2(웹 + 디자인) skeleton 코드 다운로드

```shell
$ git clone https://lab.ssafy.com/ssafyadmin/webmobile2-skeleton.git
Cloning into 'webmobile2-skeleton'...
remote: Enumerating objects: 248, done.
remote: Counting objects: 100% (248/248), done.
remote: Compressing objects: 100% (218/218), done.
remote: Total 248 (delta 8), reused 179 (delta 4)
Receiving objects: 100% (248/248), 3.78 MiB | 9.92 MiB/s, done.
Resolving deltas: 100% (8/8), done.
```

```shell
$ cd webmobile2-skeleton/
$ ls
back-sk/  front-sk/  wireframe/
```

##### 로컬에서 프론트 실행 및 웹 브라우져로 접속(http://localhost:8080)해서 확인

```shell
$ cd front-sk
$ yarn install
...
$ yarn serve
...
```

잘 작동한다.

##### dokerfile을 작성 후 프론트 코드용 도커 이미지 빌드(Dockerfile 참고: https://kr.vuejs.org/v2/cookbook/dockerize-vuejs-app.html)

```shell
$ vi Dockerfile
```

```dockerfile
# Dockerfile
FROM node:lts-alpine
RUN npm install -g http-server
WORKDIR /app
COPY package*.json ./
RUN npm install --production
COPY . .
RUN npm run build
EXPOSE 8080
CMD [ "http-server", "dist" ]
```

```shell
$ docker build . -t front:0.1
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
front               0.1                 ad2cc591eddb        5 minutes ago       317MB
node                lts-alpine          927d03058714        3 weeks ago         88.1MB
```

##### 이미지에 TAG 추가하기

```shell
$ docker tag front:0.1 front:latest
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
front               0.1                 ad2cc591eddb        5 minutes ago       317MB
front               latest              ad2cc591eddb        5 minutes ago       317MB
node                lts-alpine          927d03058714        3 weeks ago         88.1MB
```

##### 이미지에 TAG 삭제하기

```shell
$ docker rmi front
Untagged: front:latest
```

```shell
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
front               0.1                 ad2cc591eddb        7 minutes ago       317MB
node                lts-alpine          927d03058714        3 weeks ago         88.1MB
```

##### 도커로 프론트 실행 및 웹 브라우져로 접속(http://localhost)해서 확인

```shell
$ docker run -it -p 80:80 --rm front:0.1
```

- 192.168.99.100 여기로 들어가니 실행되었다.

- --rm은 컨테이너가 종료되면 자동으로 삭제되는 옵션이다. 테스트용으로 컨테이너를 실행시킬 때 유용하다.

### Skeleton 코드 기반 도커 이미지 제작 (백엔드)

#####  로컬에서 백엔드 실행 및 확인(http://localhost:8080/swagger-ui.html)

```shell
$ cd back-sk
$ ./mvnw package
$ java -jar target/webcuration-0.0.1-SNAPSHOT.jar
```

- 정상적으로 실행 되었다.

##### Dockerfile 작성 후 백엔드 코드용 도커 이미지 빌드

```shell
$ vi Dockerfile
```

```dockerfile
# dockerfile
FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

```shell
$ docker build . -t back:0.1
```

- 잘 빌드되었다.

##### 도커로 백엔드 실행 및 확인(http://192.168.99.100:8080/swagger-ui.html)

```shell
$ docker run -it -p 8080:8080 --rm back:0.1
```

- 잘 실행된다.

##### 개발용 DB로 사용할 MySQL 컨테이너 실행 및 정상 실행 여부 조회(docker ps)

```shell
$ docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=ssafyssafyroomroom -e MYSQL_DATABASE=ssafy -d mysql --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```

```shell
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                               NAMES
08c987b01a3b        mysql               "docker-entrypoint.s…"   6 seconds ago       Up 4 seconds        0.0.0.0:3306->3306/tcp, 33060/tcp   mysql
```

- 실행하니까 작동한다.
- -e 옵션은 컨테이너 OS의 환경 변수로 전달하는 방법이다. mysql 컨테이너가 실행되면 호출되는 docker-entrypoint.sh 에서는 전달된 환경변수와 command argument(--character-set-server=utf8mb4 ...) 등 사용자가 원하는 password, db 명 등을 전달받아 설정해 준다. 

##### MySQL 서버 컨테이너 안에 포함되어 있는 mysql 클라이언트 명령어를 실행시켜 DB 접속 테스트

```shell
$ docker exec -it mysql mysql -uroot -p ssafy
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 8
Server version: 8.0.19 MySQL Community Server - GPL

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show database
    -> ;
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'database' at line 1
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| ssafy              |
| sys                |
+--------------------+
5 rows in set (0.00 sec)

mysql> exit
Bye
```

- 잘 실행되었다.

