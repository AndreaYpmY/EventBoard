import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../auth/auth.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {ResetPasswordDialogComponent} from "../reset-password-dialog/reset-password-dialog.component";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup
  constructor(private authService: AuthService, private router: Router, private dialog: MatDialog) { }



  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    });
  }


  /** User information **/



  /** UI elements **/
  /* password */
  hidePassword = true;

  onSubmit() {
    const username = this.loginForm.value.username
    const password = this.loginForm.value.password

    this.authService.signIn(username, password).subscribe({
      next: (response: any) =>{
        const token = response.token

        // sets local storage variable for automatic logins
        localStorage.setItem('token', JSON.stringify(token))
        localStorage.setItem('username', JSON.stringify(username))

        if(localStorage.getItem('token')){
          this.authService.getData(username).subscribe({
            next: (userData: any) => {
              this.authService.createUser(
                userData.id,
                userData.name,
                userData.lastName,
                userData.username,
                userData.email,
                userData.role,
                token,
                userData.preferences,
                userData.position,
                userData.is_not_locked
              )
              this.authService.isLoggedIn = true
            },
            error: error => { this.errorHandler(error.status) },
          })

          this.router.navigate([''])
        }
      },
      error: error => { this.errorHandler(error.status) }
    })
  }

  onForgotPassword() {
    let dialogRef = this.dialog.open(ResetPasswordDialogComponent, {
      data: {
        username: this.loginForm.value.username,
        operationConfirmed: false
      }, disableClose: true
    })

    dialogRef.afterClosed().subscribe(result => {
      if(result.operationConfirmed){
        this.sendPasswordResetRequest(result.username)
      }
    })
  }

  private sendPasswordResetRequest(username: string){
    this.authService.resetPassword(username).subscribe({
      next: () => {
        alert("La tua password è stata resettata con successo! Controlla la tua email per maggiori informazioni")
      },
      error: () => { alert("ERRORE: Qualcosa è andato storto, riprova") }
    })
  }

  private errorHandler(error: number){
    switch (error) {
      case 403:
        alert("ERRORE: Le credenziali inserite non sono valide! Controlla e riprova")
        break
      default:
        alert("ERRORE: Errore sconosciuto")
        break
    }
  }
}
