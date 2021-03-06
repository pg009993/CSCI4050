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
            <a class="navbar-brand" href="books.jsp">Books</a>
        </div>
   
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Admin <b class="caret"></b></a>
                <ul class="dropdown-menu">
                   
                </ul>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
            <li class="active">
                    <a href="AddAdmin.jsp"><i class="fa fa-fw fa-table"></i> Admins</a>
                </li>
                <li class="active">
                    <a href="books.jsp"><i class="fa fa-fw fa-table"></i> Books</a>
                </li>
                <li class="active">
                    <a href="AddBook.jsp"><i class="fa fa-fw fa-table"></i>Add Books</a>
                </li>
                <li class="active">
                    <a href="orders.jsp"><i class="fa fa-fw fa-table"></i> Orders</a>
                </li>
                  <li class="active">
                    <a href="EditOrder.jsp"><i class="fa fa-fw fa-table"></i>Edit Order</a>
                </li>
                 <li class="active">
                    <a href="EODSR.jsp"><i class="fa fa-fw fa-table"></i> End of Day Sales Report</a>
                </li>
                 <li class="active">
                    <a href="EODLI.jsp"><i class="fa fa-fw fa-table"></i> End of Day Low Inventory Report</a>
                </li>
                <li class="active">
                    <a href="users.jsp"><i class="fa fa-fw fa-table"></i> Users</a>
                </li>
                <li class="active">
                    <a href="promos.jsp"><i class="fa fa-fw fa-table"></i> Promos</a>
                </li>
                <li class="active">
                    <a href="suspendedusers.jsp"><i class="fa fa-fw fa-table"></i> Suspended Users</a>
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
                        Users
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="books.jsp">Home</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-table"></i> Tables
                        </li>
                    </ol>
                </div>
            </div>


            <!-- /.row -->

            <div class="row">
                <div class="col-lg-14">
                    <h2> Users </h2>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Gender</th>
                                <th>City</th>
                                <th>State</th>
                                <th>Street</th>
                                <th>Password</th>
                                <th>Date Of Birth</th>
                                <th>Phone Number</th>
                                <th>Username</th>
                                <th>Zip Code</th>
                                <th></th>
                                <th></th>
                            </tr>
                            </thead>



                            <tbody>
                            <%@page import="java.sql.*"%>
							<%
								try
								{
								Class.forName("com.mysql.jdbc.Driver");
								String url="jdbc:mysql://localhost:3306/ecommerce";
								String username="root";
								String password="paw22nvc3";
								String query="select * from users";
								Connection conn=DriverManager.getConnection(url,username,password);
								Statement stmt=conn.createStatement();
								ResultSet rs=stmt.executeQuery(query);
								while(rs.next())
								{
								
								%>
                            <tr>
                                <td><%=rs.getString("first_name")%></td>
                                <td><%=rs.getString("last_name")%></td>
                                <td><%=rs.getString("email")%></td>
                                <td><%=rs.getString("gender")%></td>
                                <td><%=rs.getString("city")%></td> 
                                <td><%=rs.getString("state")%></td>
                                <td><%=rs.getString("street")%></td>
                                <td><%=rs.getString("password")%></td>
                                <td><%=rs.getString("DOB")%></td>
                                <td><%=rs.getString("phone")%></td>
                                <td><%=rs.getString("username")%></td>
                                <td><%=rs.getString("zip")%></td>
                                <th>
                                	<form action="BookServlet" method="post">
                                		<input name="username" type="hidden" value="<%=rs.getString("username") %>">
                                		<input type="submit" value="Remove User" name="removeUser">
                                	</form>				
                                </th>
                               <th>
                                	<form action="BookServlet" method="post">
                                		<input name="username" type="hidden" value="<%=rs.getString("username") %>">
                                		<input type="submit" value="Suspend User" name="suspendUser">
                                	</form>				
                                </th>
                            </tr>
                            <%}
    							rs.close();
   							    stmt.close();
   							    conn.close();
  								}
								catch(Exception e)
								{
								    e.printStackTrace();
								}
								%>
   
                            </tbody>
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