<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin - Bootstrap Admin Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.admin.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">SB Admin</a>
            </div>
            <!-- Top Menu Items -->
        
               
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>



            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
            <li class="active">
                    <a href="AddAdmin.html"><i class="fa fa-fw fa-table"></i> Admins</a>
                </li>
                <li class="active">
                    <a href="books.html"><i class="fa fa-fw fa-table"></i> Books</a>
                </li>
                <li class="active">
                    <a href="AddBook.html"><i class="fa fa-fw fa-table"></i>Add Books</a>
                </li>
                <li class="active">
                    <a href="orders.html"><i class="fa fa-fw fa-table"></i> Orders</a>
                </li>
                  <li class="active">
                    <a href="EditOrder.html"><i class="fa fa-fw fa-table"></i>Edit Order</a>
                </li>
                 <li class="active">
                    <a href="EODSR.html"><i class="fa fa-fw fa-table"></i> End of Day Sales Report</a>
                </li>
                 <li class="active">
                    <a href="EODLI.html"><i class="fa fa-fw fa-table"></i> End of Day Low Inventory Report</a>
                </li>
                <li class="active">
                    <a href="users.html"><i class="fa fa-fw fa-table"></i> Users</a>
                </li>
                <li class="active">
                    <a href="promos.html"><i class="fa fa-fw fa-table"></i> Promos</a>
                </li>
                <li class="active">
                    <a href="suspendedusers.html"><i class="fa fa-fw fa-table"></i> Suspended Users</a>
                </li>
                <li>
                    <a href="forms.html"><i class="fa fa-fw fa-edit"></i> Forms</a>
                </li>
                <li>
                    <a href="bootstrap-elements.html"><i class="fa fa-fw fa-desktop"></i> Bootstrap Elements</a>
                </li>
                <li>
                    <a href="bootstrap-grid.html"><i class="fa fa-fw fa-wrench"></i> Bootstrap Grid</a>
                </li>
                <li>
                    <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Dropdown <i class="fa fa-fw fa-caret-down"></i></a>
                    <ul id="demo" class="collapse">
                        <li>
                            <a href="#">Dropdown Item</a>
                        </li>
                        <li>
                            <a href="#">Dropdown Item</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="blank-page.html"><i class="fa fa-fw fa-file"></i> Blank Page</a>
                </li>
                <li>
                    <a href="books.html"><i class="fa fa-fw fa-dashboard"></i>Books</a>
                </li>
            </ul>
        </div>

            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Add New Admin
                        </h1>
                        <ol class="breadcrumb">
                           
                            <li class="active">
                                <i class="fa fa-edit"></i> Add Admin
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

			<!--JS function to ensure passwords match--> 
			   <script type="text/javascript">
			   	function checkPassword(){
			   		var password = $("#password").val();
			   		var password2 = $("#password2").val();
			   	
			   	if(password != password2){
   					 $("#nonMatch").html("Passwords do not match!");
   					 $('#submit').prop('disabled', true);
			   		} 
			   		else{
			   			$("#nonMatch").html("Passwords match!");
			   			$('#submit').prop('disabled', false);
			   		}
			   	}

			   $(document).ready(function(){
   					$("#password, #password2").keyup(checkPassword);
				});
			   </script>
			   
                <div class="row">
                    <div class="col-lg-6">

                        <form action="BookServlet" method="post">

                            <div class="form-group">
                                <label>Name</label>
                                <input class="form-control" name="name" required>
                            </div> 
                            <div class="form-group">
                                <label>Email</label>
                                <input class="form-control" name="email" required>
                            </div> 
                            <div class="form-group">
                                <label>Phone Number</label>
                                <input class="form-control" name="number" required>
                            </div> 
                            <div class="form-group">
                                <label>Assigned Password</label>
                                <input type="password" onchange="checkPassword()" name="password" id="password" required>                            </div> 
                             <div class="form-group">
                                <label>Confirm Password</label>
                                <input type="password" onchange="checkPassword()" name="password2" id="password2" required>
                            </div> 
                            <input type="submit" name="newAdmin" value="Add Admin" />
                            </form>
                    </div>
                </div>

<%@page import="java.sql.*"%>
<%
	try{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/ecommerce";
		String username="root";
		String password="paw22nvc3";
		String query="select * from admins";
		Connection conn=DriverManager.getConnection(url,username,password);
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		
%>


            <div class="row">
                <div class="col-lg-13">
                    <h2> Admins </h2>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                                                        <tr>
                                <td>Name</td>
                                <td>Email</td>
                                <td>Phone Number</td>
                            </tr>
                            <%while(rs.next()) { %>
                            <tr>
                                <th><%=rs.getString("name")%></th>
                                <th><%=rs.getString("email")%></th>
                                <th><%=rs.getString("phone")%></th>
                            </tr>
                            <% } %>
                            
				<%
    								rs.close();
   							    stmt.close();
   							    conn.close();
  								  }
								catch(Exception e)
								{
								    e.printStackTrace();
								    }
								%>
                            </thead>

               
                            <r id= "userTable"></r>
                        </table>
                    </div>
                </div>

                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->


        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>