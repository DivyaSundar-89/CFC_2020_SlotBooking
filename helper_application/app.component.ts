import { Component } from '@angular/core';
import {NgForm} from '@angular/forms';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {  throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.css' ]
})
export class AppComponent  {
  address: String
category: String
donornotes: String
email: String
name: String
phone: String
recommend: String
time: String
alert:boolean = false
  constructor(private http: HttpClient) {}
  

  onSubmit(form: NgForm) {
    var formData: any = new FormData();
    formData.append("name", form.value.name);
    formData.append("email", form.value.email);
    formData.append("donornotes", form.value.donornotes);
    formData.append("phone", form.value.phone);
    formData.append("recommend", form.value.recommend);
    formData.append("time", form.value.time);
    formData.append("address", form.value.address);
    formData.append("category", form.value.category);
   
      let url = "http://localhost:8083/storeDonorDetail"
      this.http.post(url, formData).subscribe(
        (response) => console.log("response" , response)       
      )
    
      console.log('Your form data : ', form.value);
      form.resetForm();
      this.alert = true;
  }
  closeAlert()
  {
    this.alert=false
  }
}