<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Suspended Users</title>

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
   
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
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
                        Users
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-table"></i> Tables
                        </li>
                    </ol>
                </div>
            </div>


            <!-- /.row -->

            <div class="row">
                <div class="col-lg-13">
                    <h2>Suspended Users </h2>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Username</th>
                                <th>Email</th>
                            </tr>
                            </thead>

               
                            <r id= "userTable"></r>
                            <tbody>
                            <%@page import="java.sql.*"%>
							<%
								try
								{
								Class.forName("com.mysql.jdbc.Driver");
								String url="jdbc:mysql://localhost:3306/ecommerce";
								String username="root";
								String password="paw22nvc3";
								String query="select * from suspendedusers";
								Connection conn=DriverManager.getConnection(url,username,password);
								Statement stmt=conn.createStatement();
								ResultSet rs=stmt.executeQuery(query);
								while(rs.next())
								{
							%>
                            <tr>
                                <td><%=rs.getString("username")%></td>
                                <%String query2="select * from users where username='" + rs.getString("username") + "'";
								Connection conn2=DriverManager.getConnection(url,username,password);
                                  Statement stmt2=conn2.createStatement();
                                  ResultSet rs2=stmt2.executeQuery(query2);
                                  while(rs2.next()) {%>
                                <td><%=rs2.getString("first_name")%></td>
                                <td><%=rs2.getString("last_name")%></td>
                                <td><%=rs2.getString("email")%></td>
                                              <th>
                                	<form action="BookServlet" method="post">
                                		<input name="username" type="hidden" value="<%=rs2.getString("username") %>">
                                		<input type="submit" value="Unsuspend User" name="unsuspendUser">
                                	</form>				
                                </th>
                                <%}
                                  rs2.close();
                                  stmt2.close();
                                  conn2.close();
								%>
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