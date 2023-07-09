import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { userlistmodel,roles } from './userlist';
import { Router } from '@angular/router';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {

  constructor(private authserv:AuthService,private route:Router){}

  userdetails:userlistmodel[]=[];
  
  todashboard(){
    this.route.navigate(['/api/users/welcome']);
  }
  tologout(){
    localStorage.removeItem('token');
    this.route.navigate(['/api/auth/login']);
  }

  ngOnInit(): void {
    this.authserv.getalluser().subscribe(result=>{
      this.userdetails=result;
    })
  }
  

}
