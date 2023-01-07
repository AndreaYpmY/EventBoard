import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {HomepageComponent} from "./components/homepage/homepage.component";
import {CreateEventComponent} from "./components/create-event/create-event.component";
import {EventComponent} from "./components/event/event.component";
import {LoginComponent} from "./components/login/login.component";
import {SearchComponent} from "./components/search/search.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {RegisterComponent} from "./components/register/register.component";
import {ProfileComponent} from "./components/profile/profile.component";

const routes: Routes = [
  {path: '', component: HomepageComponent},
  {path: 'create-event', component: CreateEventComponent},
  {path: 'event', component: EventComponent},
  {path: 'login', component: LoginComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'search', component: SearchComponent},
  {path: '**', component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
