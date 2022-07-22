import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/service/authentication.service';
import { apiUrl } from './constant';


@Injectable({
    providedIn: "root"
})
export class RequestInterceptor implements HttpInterceptor {

    constructor(private authService: AuthenticationService) {

    }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const loggedIn: boolean = this.authService.isLoggedIn();
        if (request.url == apiUrl) {
            return next.handle(request);
        }
        if (!loggedIn) {
            return next.handle(request);
        }
        const token: string | null = this.authService.getToken();
        if (token != null) {
            const headers: HttpHeaders = new HttpHeaders({ token });
            request = request.clone({ headers });
        }
        return next.handle(request);
    }

}