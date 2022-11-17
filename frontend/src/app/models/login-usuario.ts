export class LoginUsuario {
    emailUsuario: string;
    password: string;

    constructor(emailUsuario: string, password: string) {
        this.emailUsuario = emailUsuario;
        this.password = password;
    }
}