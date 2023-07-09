import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm:any;
  constructor(private authservice:AuthService,private router:Router) { }
  user:boolean=false
  admin:boolean=false
  toregister(){
    this.router.navigate(['/api/auth/signup']);
  }

  togetalluser(){
    this.authservice.getalluser().subscribe(result=>{
      console.log(result);
    })
  }
  
  ngOnInit() {
    this.initform();
  }

  initform(){
    this.loginForm=new FormGroup({
      email:new FormControl("",[Validators.required]),
      password:new FormControl("",[Validators.required]),
    });
    }
  onSubmit(){
    setTimeout(()=>this.fetch(),200)
  }

  fetch(){
    if(this.loginForm.valid){
      console.log(this.loginForm.value)
      this.authservice.login(this.loginForm.value).subscribe(result=>{
        
        this.authservice.settoken('token',result[0].token,result[2].username);
        console.log(result[2].roles);
        this.useroradmin(result[2].roles);
        if(this.admin){
          this.router.navigate(['/api/users/list']);
        }
        else
        this.router.navigate(['/api/users/welcome']);
      })
    }

  }

  useroradmin(result:any){
      result.forEach((val:any)=>{
        if(val.name=="ROLE_ADMIN"){
          this.admin=true;
        }
      })

      if(this.admin==false){
        this.user=true;
      }
      
  }
}
