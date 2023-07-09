import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  
  username:any
  constructor(private httpclient: HttpClient) { }

  login(data:any):Observable<any>{
    return this.httpclient.post("http://localhost:8080/api/auth/login",data);
  }

  signup(data:any):Observable<any>{
    return this.httpclient.post("http://localhost:8080/api/auth/signup",data);
  }

  getalluser(): Observable<any> {
    console.log(new window.Response().headers);
    return this.httpclient.get<any>("http://localhost:8080/api/users/list");
  }

  getuserbyid(id:any): Observable<any> {
    console.log(new window.Response().headers);
    return this.httpclient.get<any>("http://localhost:8080/api/auth/user"+id);
  }

  settoken(key: any, value: any, name: any) {
        const item = {
          value: value,
          name: name
        }
        localStorage.setItem(key, JSON.stringify(item))
      }
      getname() {
        const itemStr = localStorage.getItem('token')
        if (!itemStr) {
          return null
        }
        const item = JSON.parse(itemStr)
        return item.name;
      }
      gettoken(): string | null {
      const itemStr = localStorage.getItem('token')
        if (!itemStr) {
          return null
        }
        const item = JSON.parse(itemStr)
        return item.value;
  }
}
