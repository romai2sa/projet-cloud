import {ErrorHandler, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {provideHttpClient} from '@angular/common/http';
import { ErrorHandlerService } from './services/errorHandlerService.service';
import { MyMonitoringService } from './services/logging.service';


@NgModule({
  imports: [
    BrowserModule
  ],

  providers: [
    provideHttpClient(),
    MyMonitoringService,
    { provide: ErrorHandler, useClass: ErrorHandlerService },
  ]
})

export class AppModule {}
