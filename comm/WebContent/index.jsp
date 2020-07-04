<%@ page language="java" import="java.io.*" import="java.sql.*"
	import="java.util.*" import="commodity.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage</title>
<meta name="description"
	content="Blueprint: A basic template for a responsive multi-level menu" />
<meta name="keywords"
	content="blueprint, template, html, css, menu, responsive, mobile-friendly" />
<meta name="author" content="Codrops" />
<link rel="shortcut icon" href="favicon.ico">
<!-- food icons -->
<link rel="stylesheet" type="text/css" href="css/organicfoodicons.css" />
<!-- demo styles -->
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<!-- menu styles -->
<link rel="stylesheet" type="text/css" href="css/component.css" />
<script src="js/modernizr-custom.js"></script>
</head>

<body>
	<div class="container">
		<header class="bp-header cf">
			<div class="dummy-logo">
				<div style="font-size: 20px; color: white;">iTRAM</div>
			</div>

		</header>
		<!-- <button class="action action--open" aria-label="Open Menu">
			<span class="icon icon--menu"></span>
		</button> -->
		<nav id="ml-menu" class="menu">
			<!-- <button class="action action--close" aria-label="Close Menu">
				<span class="icon icon--cross"></span>
			</button> -->
			<div class="menu__wrap">
				<ul data-menu="main" class="menu__level" tabindex="-1" role="menu">
					<li class="menu__item" role="menuitem">    Vendor</li>
					<li class="menu__item" role="menuitem"><a class="menu__link"
						href="#" onclick="displayVendor();">Vendor Registration</a></li>
					<li class="menu__item" role="menuitem"><a class="menu__link"
						href="#" onclick="displayStockDetails();">Stock Details</a></li>
					<li class="menu__item" role="menuitem"><a class="menu__link"
						href="#" onclick="displaySlotDetails();">Slot Details</a></li>
					<li class="menu__item" role="menuitem"><a class="menu__link"
						href="#" onclick="displayVendorDashboard();">Vendor Dashboard</a></li>

					<li class="menu__item" role="menuitem">    Customer</li>
					<li class="menu__item" role="menuitem"><a class="menu__link"
						href="#" onclick="displayCustomerRegistration();">Customer
							Registration</a></li>
					<li class="menu__item" role="menuitem"><a class="menu__link"
						href="#" onclick="displayPlaceOrder();">Place Order</a></li>
					<li class="menu__item" role="menuitem"><a class="menu__link"
						href="#" onclick="displayOrderDetails();">Order Details</a></li>
				</ul>
			</div>
		</nav>
		<div class="content"
			style="background-color: white; height: 520px; width: 100%;">
			<!--div style = "display:none"-->

			<form name="vendorDetails" method="post" action="vendor">
				<table class="info" id="vendor"
					style="display: none; background-color: white; height: 520px; width: 50%; padding-left: 100px;">
					<tr>
						<td colspan="3"><h1>Vendor Registration</h1></td>
					</tr>
					<tr>
						<td class="colname"><b>Vendor Id</b></td>
						<td>:</td>
						<td><input type="text" name="vId" id="vId"></td>
					</tr>
					<tr>
						<td class="colname"><b>Name</b></td>
						<td>:</td>
						<td><input type="text" name="name" id="name" value=""></td>
					</tr>
					<tr>
						<td class="colname"><b>Category</b></td>
						<td>:</td>
						<td><select name="category">
								<option>Choose Category</option>
								<option value="grocery">Grocery</option>
								<option value="Vegetable">Vegetable</option>
								<option value="Hospital">Hospital</option>
								<option value="HomeAppliances">HomeAppliances</option>
						</select></td>
						<td><span id="s2"></span></td>
					</tr>
					<tr>
						<td class="colname"><b>Address</b></td>
						<td>:</td>
						<td><textarea rows="5" cols="20" name="address" id="address"></textarea></td>
						<td><span id="s3"></span></td>
					</tr>
					<tr>
						<td class="colname"><b>Contact</b></td>
						<td>:</td>
						<td><input type="text" name="contact" id="contact"
							maxlength="10"></td>
						<td><span id="s4"></span></td>


						<td align="center"><input type="submit" name="sub" id="sub"
							value="Store" onclick="Check();"></td>
				</table>
			</form>

			<!-- Place Order -->
			<form name="placeOrder" method="post" action="placeOrder">
				<table class="info" id="placeOrder"
					style="display: none; background-color: white; height: 520px; width: 50%; padding-left: 100px;">
					<tr>
						<td colspan="3"><h1>Place Order</h1></td>
					</tr>
					<tr>
						<td class="colname"><b>Customer</b></td>
						<td>:</td>
						<td><input type="text" name="customer" id="customer"></td>
					</tr>
					<tr>
						<td class="colname"><b>Category</b></td>
						<td>:</td>
						<td><select name="category">
								<option>Choose Category</option>
								<option value="grocery">Grocery</option>
								<option value="Vegetable">Vegetable</option>
								<option value="Hospital">Hospital</option>
								<option value="HomeAppliances">HomeAppliances</option>
						</select></td>
						<td><span id="s2"></span></td>
					</tr>
					<tr>
						<td class="colname"><b>Item</b></td>
						<td>:</td>
						<td><input type="text" name="item" id="item" value=""></td>
					</tr>
					<tr>
						<td class="colname"><b>Quantity</b></td>
						<td>:</td>
						<td><input type="text" name="quantity" id="quantity" value=""></td>
					</tr>

					<td align="center"><input type="submit" name="sub" id="sub"
						value="Store" onclick="Check();"></td>
				</table>
			</form>


			<!-- Slot Details -->
			<form name="slotDetails" method="post" action="slot">
				<table class="info" id="slotDetails"
					style="display: none; background-color: white; height: 520px; width: 80%; padding-left: 100px;">
					<tr>
						<td colspan="3"><h1>Slot Details</h1></td>
					</tr>
					<tr>
						<td class="colname"><b>Slot Time Period</b></td>
						<td>:</td>
						<td align=left><input type="text" name="iSlotPeriod"
							id="iSlotPeriod" onkeypress="return isNumberKey(event)"></td>
					</tr>
					<tr>
						<td class="colname"><b>Regular Working Days</b></td>
						<td>:</td>
						<td align=left><input type="checkbox" name="workingDay"
							value="monday" />Monday <input type="checkbox" name="workingDay"
							value="tuesday" />Tuesday <input type="checkbox"
							name="workingDay" value="wednesday" />Wednesday <input
							type="checkbox" name="workingDay" value="thursday" />Thursday<br />
							<input type="checkbox" name="workingDay" value="friday" />Friday
							<input type="checkbox" name="workingDay" value="saturday" />Saturday
							<input type="checkbox" name="workingDay" value="sunday" />Sunday</td>
					</tr>
					<tr>
						<td class="colname"><b>Slot Opening Time</b></td>
						<td>:</td>
						<td align=left><select name="oSlot">
								<option>Choose Opening Time</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
						</select> <input type="radio" name="oTime" value="am" />AM <input
							type="radio" name="oTime" value="pm" />PM</td>
					<tr>
						<td class="colname"><b>Slot Closing Time</b></td>
						<td>:</td>
						<td align=left><select name="cSlot">
								<option>Choose Closing Time</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
						</select> <input type="radio" name="cTime" value="am" />AM <input
							type="radio" name="cTime" value="pm" />PM</td>
					<tr>
						<td class="colname"><b>Slot Exclusion Period</b></td>
						<td>:</td>
						<td align=left><select name="sExcFrom">
								<option>From Time</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
						</select> <input type="radio" name="exFrTime" value="am" />AM <input
							type="radio" name="exFrTime" value="pm" />PM <br /> TO <br />
							<select name="sExcTo">
								<option>To Time</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
						</select> <input type="radio" name="exToTime" value="am" />AM <input
							type="radio" name="exToTime" value="pm" />PM</td>
					<tr>
						<td class="colname"><b>Vendor</b></td>
						<td>:</td>
						<td align=left><input type="text" name="vId" id="vId"></td>
						<td><span id="s2"></span></td>
					</tr>

					<td align="center"><input type="submit" name="sub" id="sub"
						value="Store" onclick="Check();"></td>
				</table>
			</form>


			<!-- Stock Details -->
			<form name="stockDetails" method="post" action="stock">
				<table class="info" id="stockDetails"
					style="display: none; background-color: white; height: 520px; width: 50%; padding-left: 100px;">
					<tr>
						<td colspan="3"><h1>Stock Details</h1></td>
					</tr>
					<tr>
						<td class="colname"><b>Type</b></td>
						<td>:</td>
						<td><input type="text" name="type" id="type" value=""></td>
					</tr>
					<tr>
						<td class="colname"><b>Item</b></td>
						<td>:</td>
						<td><input type="text" name="item" id="item"></td>
					<tr>
						<td class="colname"><b>Item Price</b></td>
						<td>:</td>
						<td><input type="text" name="price" id="price"
							onkeypress="return isNumberKey(event)"></td>
					<tr>
						<td class="colname"><b>Stock</b></td>
						<td>:</td>
						<td><input type="text" name="stock" id="stock"
							onkeypress="return isNumberKey(event)"></td>
					<tr>
						<td class="colname"><b>Category</b></td>
						<td>:</td>
						<td><select name="category">
								<option>Choose Category</option>
								<option value="grocery">Grocery</option>
								<option value="Vegetable">Vegetable</option>
								<option value="Hospital">Hospital</option>
								<option value="HomeAppliances">HomeAppliances</option>
						</select></td>
					<tr>
						<td class="colname"><b>Vendor</b></td>
						<td>:</td>
						<td><input type="text" name="vendor" id="vendor"></td>
						<td><span id="s2"></span></td>
					</tr>

					<tr>
						<!-- td align="center" ><input type="reset" value="Reset"></td-->
						<td align="center"><input type="submit" name="sub" id="sub"
							value="Store" onclick="Check();"></td>
				</table>
			</form>

			<!-- Vendor Dashboard -->
			<form name="vendorDashboard" method="get" action="vendor?flag=1">
				<table class="info" id="vendorDashboard"
					style="display: none; background-color: white; height: 500px; width: 50%; padding-left: 100px;">
					<tr>
						<td colspan="3"><h1>Vendor Dashboard</h1></td>
					</tr>
					<tr>
						<td class="colname"><b>Vendor</b></td>
						<td>:</td>
						<td><select name="vValue" id="dp">
								<option value="0">Choose Vendor</option>
								<%
									String DRIVER1 = "com.mysql.cj.jdbc.Driver";
																	Class.forName(DRIVER1).newInstance();
																	Connection con1 = null;
																	ResultSet rst1 = null;
																	Statement stmt1 = null;
																	Connection con2 = null;
																	ResultSet rst2 = null;
																	Statement stmt2 = null;

																	try {
																		String url = "jdbc:mysql://localhost:3306/cus?user=root&password=root";

																		int i = 1;
																		con1 = DriverManager.getConnection(url);
																		stmt1 = con1.createStatement();
																		rst1 = stmt1.executeQuery("select id, name from vendor");
																		while (rst1.next()) 
																		{
								%>

								<option value="<%=rst1.getInt(1)%>"><%=rst1.getString(2)%></option>
								<%
									}
								%>
						</select> <input type="hidden" id="hiddenField" name="hiddenField" /> <input
							type="submit" id="submit" name="Submit" value="Check" /></td>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<th>Customer Name</th>
						<th>Order ID</th>
						<th>Slot</th>
						<th>Total Price</th>
					</tr>
					<tr>
						<%
							List<vendorBean> list = (List)request.getSession().getAttribute("arrayList");
														if(list!=null || !list.isEmpty())
														{
															for(vendorBean bean : list)
															{
						%>
						<td><%=bean.getCustomer_name()%></td>
						<td><%=bean.getoId()%></td>
						<td><%=bean.getSlot()%></td>
						<td><%=bean.getTotal_price()%></td>
					</tr>
					<%
						}
											}
											rst1.close();
											stmt1.close();
											con1.close();
											rst2.close();
											stmt2.close();
											con2.close();
											} catch (Exception e) {
												System.out.println(e.getMessage());
											}
					%>
				</table>
			</form>

			<!-- Customer Registration -->
			<form name="customerRegistration" method="post" action="customer">
				<table class="info" id="customerRegistration"
					style="display: none; background-color: white; height: 520px; width: 50%; padding-left: 100px;">
					<tr>
						<td colspan="3"><h1>Customer Registration</h1></td>
					</tr>
					<tr>
						<td class="colname"><b>Name</b></td>
						<td>:</td>
						<td><input type="text" name="name" id="name" value=""></td>
					</tr>
					<tr>
						<td class="colname"><b>Identity Number</b></td>
						<td>:</td>
						<td><input type="text" name="cId" id="cId"></td>
						<td><span id="s2"></span></td>
					</tr>
					<tr>
						<td class="colname"><b>Address</b></td>
						<td>:</td>
						<td><textarea rows="5" cols="20" name="address" id="address"></textarea></td>
						<td><span id="s3"></span></td>
					</tr>
					<tr>
						<td class="colname"><b>Contact</b></td>
						<td>:</td>
						<td><input type="text" name="contact" id="contact"
							maxlength="10"></td>
						<td><span id="s4"></span></td>
					<tr>
						<!-- td align="center" ><input type="reset" value="Reset"></td-->
						<td align="center"><input type="submit" name="sub" id="sub"
							value="Store" onclick="Check();"></td>
				</table>
			</form>

			<!-- Order -->
			<table class="info" id="orders"
				style="display: none; background-color: white; height: 520px; width: 50%; padding-left: 100px;">
				<tbody border=2>
					<tr>
						<td align="center" colspan="11"><h1>Order Details</h1></td>
					</tr>
					<td align="center" height="19"><b>Order ID</b></td>
					<td align="center" height="19"><b>Customer Name</b></td>
					<td align="center" height="19"><b>Vendor ID</b></td>
					<td align="center" height="19"><b>Slot</b></td>
					<td align="center" height="19"><b>Total Price</b></td>

					<%
						String DRIVER = "com.mysql.cj.jdbc.Driver";
											Class.forName(DRIVER).newInstance();

											Connection con = null;
											ResultSet rst = null;
											Statement stmt = null;

											try {									
												String url = "jdbc:mysql://localhost:3306/cus?user=root&password=root";
												con = DriverManager.getConnection(url);
												stmt = con.createStatement();
												rst = stmt.executeQuery("select oId, customer_name, id, slot, total_price from orders");
												while (rst.next()) {
					%>
					<tr>
						<td vAlign="top" height="19"><%=rst.getInt(1)%></td>
						<td vAlign="top" height="19"><%=rst.getString(2)%></td>
						<td vAlign="top" height="19"><%=rst.getInt(3)%></td>
						<td vAlign="top" height="19"><%=rst.getInt(4)%></td>
						<td vAlign="top" height="19"><%=rst.getInt(5)%></td>
					</tr>
					<%
						}
											rst.close();
											stmt.close();
											con.close();
											} catch (Exception e) {
												System.out.println(e.getMessage());
											}
					%>

				</tbody>
			</table>



		</div>
		<div style="height: 70px;"">
			<p style="padding-left: 600px;">©</p>
		</div>
	</div>
	<!-- /view -->
	<script src="js/classie.js"></script>
	<script src="js/dummydata.js"></script>
	<script src="js/main.js"></script>
	<script>
		function displayVendor() {
			document.getElementById("vendor").style.display = "block";
			document.getElementById("stockDetails").style.display = "none";
			document.getElementById("customerRegistration").style.display = "none";
			document.getElementById("slotDetails").style.display = "none";
			document.getElementById("orders").style.display = "none";
			document.getElementById("vendorDashboard").style.display = "none";
			document.getElementById("placeOrder").style.display = "none";
		}
		function displayCustomerRegistration() {
			document.getElementById("vendor").style.display = "none";
			document.getElementById("stockDetails").style.display = "none";
			document.getElementById("customerRegistration").style.display = "block";
			document.getElementById("slotDetails").style.display = "none";
			document.getElementById("orders").style.display = "none";
			document.getElementById("vendorDashboard").style.display = "none";
			document.getElementById("placeOrder").style.display = "none";
		}
		function displayStockDetails() {
			document.getElementById("vendor").style.display = "none";
			document.getElementById("stockDetails").style.display = "block";
			document.getElementById("customerRegistration").style.display = "none";
			document.getElementById("slotDetails").style.display = "none";
			document.getElementById("orders").style.display = "none";
			document.getElementById("vendorDashboard").style.display = "none";
			document.getElementById("placeOrder").style.display = "none";
		}
		function displaySlotDetails() {
			document.getElementById("vendor").style.display = "none";
			document.getElementById("stockDetails").style.display = "none";
			document.getElementById("customerRegistration").style.display = "none";
			document.getElementById("slotDetails").style.display = "block";
			document.getElementById("orders").style.display = "none";
			document.getElementById("vendorDashboard").style.display = "none";
			document.getElementById("placeOrder").style.display = "none";
		}
		function displayOrderDetails() {
			document.getElementById("vendor").style.display = "none";
			document.getElementById("stockDetails").style.display = "none";
			document.getElementById("customerRegistration").style.display = "none";
			document.getElementById("slotDetails").style.display = "none";
			document.getElementById("orders").style.display = "block";
			document.getElementById("vendorDashboard").style.display = "none";
			document.getElementById("placeOrder").style.display = "none";
		}
		function displaySlotConfiguration() {
			document.getElementById("vendor").style.display = "none";
			document.getElementById("stockDetails").style.display = "none";
			document.getElementById("customerRegistration").style.display = "none";
			document.getElementById("slotDetails").style.display = "none";
			document.getElementById("orders").style.display = "none";
			document.getElementById("vendorDashboard").style.display = "none";
			document.getElementById("placeOrder").style.display = "none";
		}
		function getComboA(selectObject) {
			var val = selectObject.value;
			document.getElementById("hiddenField").value = val;
			alert(document.getElementById("hiddenField").value);
		}
		function displayVendorDashboard() {
			document.getElementById("vendor").style.display = "none";
			document.getElementById("stockDetails").style.display = "none";
			document.getElementById("customerRegistration").style.display = "none";
			document.getElementById("slotDetails").style.display = "none";
			document.getElementById("orders").style.display = "none";
			document.getElementById("vendorDashboard").style.display = "block";
			document.getElementById("placeOrder").style.display = "none";
		}
		function displayPlaceOrder() {
			document.getElementById("vendor").style.display = "none";
			document.getElementById("stockDetails").style.display = "none";
			document.getElementById("customerRegistration").style.display = "none";
			document.getElementById("slotDetails").style.display = "none";
			document.getElementById("orders").style.display = "none";
			document.getElementById("vendorDashboard").style.display = "none";
			document.getElementById("placeOrder").style.display = "block";
		}
		(function() {
			var menuEl = document.getElementById('ml-menu'), mlmenu = new MLMenu(
					menuEl, {
						// breadcrumbsCtrl : true, // show breadcrumbs
						// initialBreadcrumb : 'all', // initial breadcrumb text
						backCtrl : false, // show back button
						// itemsDelayInterval : 60, // delay between each menu item sliding animation
						onItemClick : loadDummyData
					// callback: item that doesn´t have a submenu gets clicked - onItemClick([event], [inner HTML of the clicked item])
					});

			// mobile menu toggle
			var openMenuCtrl = document.querySelector('.action--open'), closeMenuCtrl = document
					.querySelector('.action--close');

			openMenuCtrl.addEventListener('click', openMenu);
			closeMenuCtrl.addEventListener('click', closeMenu);

			function openMenu() {
				classie.add(menuEl, 'menu--open');
				closeMenuCtrl.focus();
			}

			function closeMenu() {
				classie.remove(menuEl, 'menu--open');
				openMenuCtrl.focus();
			}

			// simulate grid content loading
			var gridWrapper = document.querySelector('.content');

			function loadDummyData(ev, itemName) {
				/* ev.preventDefault();

				closeMenu();
				gridWrapper.innerHTML = '';
				classie.add(gridWrapper, 'content--loading');
				setTimeout(function() {
					classie.remove(gridWrapper, 'content--loading');
					gridWrapper.innerHTML = '<ul class="products">'
							+ dummyData[itemName] + '<ul>';
				}, 700); */
			}
		})();
	</script>
</body>
</html>