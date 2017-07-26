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
            <a class="navbar-brand">Welcome Admin!</a>
           
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
  <li> <form action="BookServlet"><button name="logoutadmin">Logout</button></form></li>
      
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>Admin<b class="caret"></b></a>
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
                        Promos
                    </h1>
                    <ol class="breadcrumb">
                       
                        <li class="active">
                            <i class="fa fa-table"></i> Tables
                        </li>
                    </ol>
                </div>
            </div>
            
            
            
              <div class="row">
                    <div class="col-lg-6">

                        <form action="BookServlet" method="post">

                            <div class="form-group">
                            <p>Create A New Promo</p>
                                <label>Code</label>
                                <input class="form-control" name="promocode">
                            </div> 
                            <div class="form-group">
                                <label>Percentage (as a whole number)</label>
                                <input class="form-control" name="percentage">
                            </div> 
                            <input type="submit" name="newPromo" />
                            </form>
                    </div>
                </div>
            
            
            
            
            <!-- /.row -->

            <div class="row">
                <div class="col-lg-3">
                    <h2> Promos </h2>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Percentage</th>
                                <th>Promotion Code</th>

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
								String query="select * from promos";
								Connection conn=DriverManager.getConnection(url,username,password);
								Statement stmt=conn.createStatement();
								ResultSet rs=stmt.executeQuery(query);
								while(rs.next())
								{
							%>
							<tr>
	                            <td><%=rs.getString("percentage")%></td>
	                            <td><%=rs.getString("code")%></td>
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