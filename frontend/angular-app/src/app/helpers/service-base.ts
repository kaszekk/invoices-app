import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";

export abstract class ServiceBase {

  protected contentType = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  protected constructor(http: HttpClient) {
  }

  protected static apiUrl(servicePath: string, id: number = null) {
    const idInUrl = id !== null ? '/' + id : '';
    return environment.urlBase + '/' + servicePath + idInUrl;
  }
}
