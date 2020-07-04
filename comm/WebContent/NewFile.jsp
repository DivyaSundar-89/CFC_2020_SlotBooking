<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
      pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Place order</title>

<style>
table {
      margin: 25px 0;
      width: 200px;
}

table th,table td {
      padding: 10px;
      text-align: center;
}

table,th,td {
      border: 1px solid;
}
</style>
</head>
<div align="center">
      <body>
            <h2>Place Order</h2>
            <form action="listtable" method="POST">
                  <TABLE id="dataTable" width="550px" border="1">
                        <TR>
                              <TH>Customer</TH>
                              <TH>Category</TH>
                              <TH>Item</TH>
                              <TH>Quantity</TH>
                        </TR>
                        <TR>
                              <TD><INPUT type="text" id="customer1" /></TD>
                              <TD><INPUT type="text" id="category1" /></TD>
                              <TD><INPUT type="text" id="item1" /></TD>
                              <TD><INPUT type="text" id="quantity1"></TD>
                        </TR>
                        <TR>
                              <TD><INPUT type="text" id="customer2" /></TD>
                              <TD><INPUT type="text" id="category2" /></TD>
                              <TD><INPUT type="text" id="item2" /></TD>
                              <TD><INPUT type="text" id="quantity2"></TD>
                        </TR>
                        <TR>
                              <TD><INPUT type="text" id="customer3" /></TD>
                              <TD><INPUT type="text" id="category3" /></TD>
                              <TD><INPUT type="text" id="item3" /></TD>
                              <TD><INPUT type="text" id="quantity3"></TD>
                        </TR>
                        <TR>
                              <TD><INPUT type="text" id="customer4" /></TD>
                              <TD><INPUT type="text" id="category4" /></TD>
                              <TD><INPUT type="text" id="item4" /></TD>
                              <TD><INPUT type="text" id="quantity4"></TD>
                        </TR>
                        <TR>
                              <TD><INPUT type="text" id="customer5" /></TD>
                              <TD><INPUT type="text" id="category5" /></TD>
                              <TD><INPUT type="text" id="item5" /></TD>
                              <TD><INPUT type="text" id="quantity5"></TD>
                        </TR>
                  </TABLE>

                  <SCRIPT language="javascript">
                        var arrayList = [];
                        function myFunction() {
                              var customer1 = document.getElementById("customer1").value
                              var category1 = document.getElementById("category1").value;
                              var item1 = document.getElementById("item1").value;
                              var quantity1 = document.getElementById("quantity1").value;
                              var employeeObject1 = new Employee(customer1, category1,
                                          item1, quantity1);
                              arrayList.push(employeeObject1);

                              var customer2 = document.getElementById("customer2").value
                              var category2 = document.getElementById("category2").value;
                              var item2 = document.getElementById("item2").value;
                              var quantity2 = document.getElementById("quantity2").value;
                              var employeeObject2 = new Employee(customer2, category2,
                                          item2, quantity2);
                              arrayList.push(employeeObject2);

                              var customer3 = document.getElementById("customer3").value
                              var category3 = document.getElementById("category3").value;
                              var item3 = document.getElementById("item3").value;
                              var quantity3 = document.getElementById("quantity3").value;
                              var employeeObject3 = new Employee(customer3, category3,
                                          item3, quantity3);
                              arrayList.push(employeeObject3);

                              var customer4 = document.getElementById("customer4").value
                              var category4 = document.getElementById("category4").value;
                              var item4 = document.getElementById("item4").value;
                              var quantity4 = document.getElementById("quantity4").value;
                              var employeeObject4 = new Employee(customer4, category4,
                                          item4, quantity4);
                              arrayList.push(employeeObject4);

                              var customer5 = document.getElementById("customer5").value
                              var category5 = document.getElementById("category5").value;
                              var item5 = document.getElementById("item5").value;
                              var quantity5 = document.getElementById("quantity5").value;
                              var employeeObject5 = new Employee(customer5, category5,
                                          item5, quantity5);
                              arrayList.push(employeeObject5);
                              alert(JSON.stringify(arrayList));

                        }

                        function Employee(customer, category, item, quantity) {
                              this.customer = customer;
                              this.category = category;
                              this.item = item;
                              this.quantity = quantity;
                        }
                  </SCRIPT>

                  <INPUT type="submit" value="Checkout" onClick="myFunction()" />
      </body>
</div>
</html>