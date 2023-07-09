import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Route, Router } from '@angular/router';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  signupForm:any;
  constructor(private authservice:AuthService,private router:Router) { }

  match:boolean=false;
  ngOnInit(): void {
    this.initform();
  }

  public get invalid():boolean{
    return this.match;
  } 
  tologin(){
    this.router.navigate(['/api/auth/login']);
  }
  initform(){
    this.signupForm=new FormGroup({
      username:new FormControl("",[Validators.required]),
      email:new FormControl("",[Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
      password:new FormControl("",[Validators.required]),
      confirmpassword:new FormControl("",[Validators.required]),
    });
    }
  
  onSubmit(){
    if(this.signupForm.valid){
      if(this.signupForm.value.password==this.signupForm.value.confirmpassword){
        this.signupForm.get('confirmpassword').disable();
        console.log(this.signupForm.value)
         this.authservice.signup(this.signupForm.value).subscribe(result=>{
        console.log(result);
      }) 
      this.router.navigate(['/api/auth/login']);
      }
      else
      this.match=true;
      

      
    }
    //location.reload();
  }

  }

