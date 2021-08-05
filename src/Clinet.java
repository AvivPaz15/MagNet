import java.sql.*;

public class Clinet {
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;

	String dbUrl = "jdbc:mysql://localhost:3306/magnet-db";
	String user = "student";
	String pass = "studnt";

	double id;
	String city;
	String street;
	int street_num;
	String infrastructure;
	String isp;
	String infrastructure_type;
	double download_speed;
	double upload_speed;
	double ping;
	double paid_speed;
	int disconnections;
	double internet_score;
	

	public Clinet(double id,String city,String street,int street_num,String infrastructure,String isp,String infrastructure_type,
			double download_speed,double upload_speed,double ping,double paid_speed,int disconnections,double internet_score) {
		this.id = id;
		this.city = city;
		this.street = street;
		this.street_num = street_num;
		this.infrastructure = infrastructure;
		this.isp = isp;
		this.infrastructure_type = infrastructure_type;
		this.download_speed = download_speed;
		this.upload_speed = upload_speed;
		this.ping = ping;
		this.paid_speed=paid_speed;
		this.disconnections = disconnections;
		this.internet_score=internet_score;
		
	}

	

	public void insert_clinet(Clinet data){
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(dbUrl, user, pass);

			System.out.println("Database connection successful!\n");

			// 3. insert new clinet
			System.out.println("Inserting a new clinet to database");
			PreparedStatement myStmt =
					myConn.prepareStatement("insert into clinet (id,city,street,street_num,infrastructure,isp,infrastructure_type,download_speed,upload_speed,ping,paid_speed,disconnections,internet_score)"
							+"values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			myStmt.setDouble(1, data.id);
			myStmt.setString(2, data.city);
			myStmt.setString(3, data.street);
			myStmt.setDouble(4, data.street_num);
			myStmt.setString(5, data.infrastructure);
			myStmt.setString(6, data.isp);
			myStmt.setString(7, data.infrastructure_type);
			myStmt.setDouble(8, data.download_speed);
			myStmt.setDouble(9, data.upload_speed);
			myStmt.setDouble(10,data.ping);
			myStmt.setDouble(11, data.paid_speed);
			myStmt.setDouble(12,data.disconnections);
			myStmt.setDouble(13,data.internet_score);
			//Execute update query
			myStmt.executeUpdate();
			System.out.println("successfuly inserted a clinet");
			// 4. Verify the client was added to the db
			myRs = myStmt.executeQuery("select * from clinet");



			// 5. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("id"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	public void update_clinet(Clinet data){

		try {
			// Get a connection to database
			myConn = DriverManager.getConnection(dbUrl, user, pass);

			//Statement to update a clinet Parameters.
			PreparedStatement myStmt =
					myConn.prepareStatement("update clinet" + "set city = ? and street = ? and street_num = ? and infrastructure = ? and isp = ? and infrastructure_type = ? and download_speed = ? and upload_speed = ? and ping = ? and paid_speed = ? and disconnections = ? and internet_score = ?" + "where id = ?" );
			myStmt.setString(1, data.city);
			myStmt.setString(2, data.street);
			myStmt.setDouble(3, data.street_num);
			myStmt.setString(4, data.infrastructure);
			myStmt.setString(5, data.isp);
			myStmt.setString(6, data.infrastructure_type);
			myStmt.setDouble(7, data.download_speed);
			myStmt.setDouble(8, data.upload_speed);
			myStmt.setDouble(9,data.ping);
			myStmt.setDouble(10, data.paid_speed);
			myStmt.setDouble(11,data.disconnections);
			myStmt.setDouble(12,data.internet_score);
			myStmt.setDouble(13, data.id);

			//Execute update query
			myRs = myStmt.executeQuery();



		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public double clinet_score(Clinet data) {
		double disconnections_score;
		double download_score;
		double upload_score;
		double semi_upload_score;
		double ping_score;
		double internet_score;
		disconnections_score= ((((-50/7)*data.disconnections)+100)/100)*4;
		download_score= (data.download_speed/data.paid_speed)*3;
		semi_upload_score=(data.upload_speed/data.paid_speed)*100;
		upload_score=(((100/7)*(semi_upload_score-10))+100)/100;
		ping_score= ((((-5/6)*data.ping)+125)/100)*2;
		internet_score = disconnections_score + download_score + upload_score + ping_score;
		return internet_score;
	}

	public void recommendation(Clinet maor){
		try {
		double[] infrastructure_score = new double[10];
		int num_clinets_infrastructure = 0;
		PreparedStatement myStmt = myConn.prepareStatement("select internet_Score from clinet where city = ? and street = ? and street_num = ? and infrastructure = ?");
		myStmt.setString(1, maor.city);
		myStmt.setString(2, maor.street);
		myStmt.setDouble(3, maor.street_num);
		myStmt.setString(4,"bezeq");
		myRs = myStmt.executeQuery();
		while(myRs.next()){
			num_clinets_infrastructure++;
			infrastructure_score[0]+=myRs.getDouble("internet_score");
		}
		infrastructure_score[0]=infrastructure_score[0]/num_clinets_infrastructure;


		num_clinets_infrastructure=0;
		myStmt.setString(1, maor.city);
		myStmt.setString(2, maor.street);
		myStmt.setDouble(3, maor.street_num);
		myStmt.setString(4,"hot");
		myRs = myStmt.executeQuery();
		while(myRs.next()){
			num_clinets_infrastructure++;
			infrastructure_score[1]+=myRs.getDouble("internet_score");
		}
		infrastructure_score[1]=infrastructure_score[1]/num_clinets_infrastructure;




		num_clinets_infrastructure=0;
		myStmt.setString(1, maor.city);
		myStmt.setString(2, maor.street);
		myStmt.setDouble(3, maor.street_num);
		myStmt.setString(4,"unlimited");
		myRs = myStmt.executeQuery();
		while(myRs.next()){
			num_clinets_infrastructure++;
			infrastructure_score[2]+=myRs.getDouble("internet_score");;
		}
		infrastructure_score[2]=infrastructure_score[2]/num_clinets_infrastructure;





		num_clinets_infrastructure=0;
		myStmt.setString(1, maor.city);
		myStmt.setString(2, maor.street);
		myStmt.setDouble(3, maor.street_num);
		myStmt.setString(4,"partnar");
		myRs = myStmt.executeQuery();
		while(myRs.next()){
			num_clinets_infrastructure++;
			infrastructure_score[3]+=myRs.getDouble("internet_score");
		}
		infrastructure_score[3]=infrastructure_score[3]/num_clinets_infrastructure;




		num_clinets_infrastructure=0;
		myStmt.setString(1, maor.city);
		myStmt.setString(2, maor.street);
		myStmt.setDouble(3, maor.street_num);
		myStmt.setString(4,"cellcom");
		myRs = myStmt.executeQuery();
		while(myRs.next()){
			num_clinets_infrastructure++;
			infrastructure_score[4]+=myRs.getDouble("internet_score");
		}
		infrastructure_score[4]=infrastructure_score[4]/num_clinets_infrastructure;
		
		String internet_recommendation = best_infrastructure(infrastructure_score);
		
		
		
		
		
		
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static String best_infrastructure(double array []) {
		double max = array[0];
		int count=0;
		for(int x=0;x<array.length;x++) {
			if(array[x]>max) {
				max=array[x];
			count=x;
			}
		}
		if(count==0 || count!=0)        
			return "bezeq";
		else if(count==1)
			return "hot";
		else if(count==2)
			return "unlimited";
		else if(count==3)
			return "partnar";
		else if(count==4)
			return "cellcom";
		else
		return "Eror";
	}







}
