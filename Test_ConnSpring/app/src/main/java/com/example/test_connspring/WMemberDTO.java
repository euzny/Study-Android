package com.example.test_connspring;

public class WMemberDTO {
	 private String id, pw, name, addr, tel,age;

	public WMemberDTO() {
	}

	public WMemberDTO(String id, String pw, String name, String addr, String tel, String age) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.addr = addr;
		this.tel = tel;
		this.age = age;
	}

	public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String getPw() {
	        return pw;
	    }

	    public void setPw(String pw) {
	        this.pw = pw;
	    }
}
