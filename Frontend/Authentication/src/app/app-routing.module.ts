import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth.guard';
import { RegisterComponent } from './register/register.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { UserlistComponent } from './userlist/userlist.component';

const routes: Routes = [
  { 
    path: 'api/auth/login', 
    component: LoginComponent
  },
  {
    path:'api/auth/signup',
    component:RegisterComponent
  },
  {
    path:'api/users/list',
    //canActivate:[AuthGuard],
    component:UserlistComponent
  },
  {
    path:'api/users/welcome',
    //canActivate:[AuthGuard],
    component:WelcomeComponent
  },
  {
    path:'',
    component:LoginComponent
  },
  {
    path: '**', 
    redirectTo:'',
    pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
