package ie.gmit.sw.client.config;

public class Context {
	//Variables--------------------------------------------------------------------------
	public static final String CONF_FILE="client-config.xml";
	private String username;
	private String host;
	private String port;
	private String dir;
	
	//constructor--------------------------------------------------------------------------
	public Context() {
		super();
	}

	//Getters & Setters--------------------------------------------------------------------------
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String downloadDir) {
		this.dir = downloadDir;
	}
	
	//Overriden methods--------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Context [username=" + username + ", host=" + host + ", port=" + port + ", dir=" + dir + "]";
	}

}
