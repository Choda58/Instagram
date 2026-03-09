import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Register } from './pages/register/register';
import { Home } from './pages/home/home';
import { Profile } from './pages/profile/profile';
import { CreatePost } from './pages/create-post/create-post';

export const routes: Routes = [

  { path: 'login', component: Login },

  { path: 'register', component: Register },

  { path: 'home', component: Home },

  { path: 'profile', component: Profile },

  { path: 'create-post', component: CreatePost },

  { path: '', redirectTo: 'login', pathMatch: 'full' }

];
