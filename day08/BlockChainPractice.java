import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class BlockChainPractice {
	public static HashMap<String , Block> BlockChain = new HashMap<String, Block>();
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		makeBlock("1st");
		makeBlock("2nd");
		makeBlock("3rd");
		System.out.println(BlockChain.get("Block1"));
		System.out.println(BlockChain.get("Block2"));
		System.out.println(BlockChain.get("Block3"));
		
	}
	
	public static String sha256(String msg) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(msg.getBytes());
		StringBuilder builder = new StringBuilder();
		for (byte b: md.digest()) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}
	
	public static void makeBlock(String data) throws NoSuchAlgorithmException {
		if (Block.getTotalNum() == 0) {
			BlockChain.put("Block" + Long.toString(Block.getTotalNum() + 1), new Block("Genesis Block", ""));
		} else {
			BlockChain.put("Block" + Long.toString(Block.getTotalNum() + 1), new Block(data, BlockChain.get("Block" + Block.totalNum).getHash()));
		}
	}
	
	public static class Block {
		private static long totalNum = 0;
		private String hash;
		private String data;
		private String prevhash;
		private long nonce;
		
		@Override
		public String toString() {
			return "nonce:" + nonce + "\n" + "data:" + data + "\n" + "prevhash:" + prevhash + "\n" + "hash:" + hash + "\n";
		}

		public Block(String data, String prevhash) throws NoSuchAlgorithmException {
			this.data = data;
			this.prevhash = prevhash;
			this.nonce = -1;
			if (prevhash == "") {
				this.hash = sha256(this.data + Long.toString(++this.nonce));
				totalNum += 1;
				
			} else {
				while (true) {
					this.hash = sha256(this.prevhash + this.data + Long.toString(++this.nonce));
					if (this.hash.startsWith("00000")) {
						totalNum += 1;
						break;
					}
				}
			}
		}

		public static long getTotalNum() {
			return totalNum;
		}

		public static void setTotalNum(long totalNum) {
			Block.totalNum = totalNum;
		}

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getPrevhash() {
			return prevhash;
		}

		public void setPrevhash(String prevhash) {
			this.prevhash = prevhash;
		}

		public long getNonce() {
			return nonce;
		}

		public void setNonce(long nonce) {
			this.nonce = nonce;
		}
		
	}
	

}
