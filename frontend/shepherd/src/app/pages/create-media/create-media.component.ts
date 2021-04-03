import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Media } from 'src/app/models/medias/media';
import { TrustReport } from 'src/app/models/medias/trust-report';
import { MediaService } from 'src/app/shared/services/media/media.service';

@Component({
  selector: 'app-create-media',
  templateUrl: './create-media.component.html',
  styleUrls: ['./create-media.component.css']
})
export class CreateMediaComponent implements OnInit {

  readonly urlRegexp = 'https://([\\da-z.-]{1,64})\\.([a-z.]{2,6})[/\\w .-]{0,256}/?';

  public createMediaForm!: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly mediaService: MediaService,
    private readonly router: Router,
    private readonly toastr: ToastrService,
  ) { }

  ngOnInit(): void {
    this.createMediaForm = this.formBuilder.group({
      name: ['', [
        Validators.required,
        Validators.minLength(Media.NAME_MIN_LENGTH),
        Validators.maxLength(Media.NAME_MAX_LENGTH),
      ]],
      description: ['', [
        Validators.required,
        Validators.minLength(Media.DESCRIPTION_MIN_LENGTH),
        Validators.maxLength(Media.DESCRIPTION_MAX_LENGTH)
      ]],
      website: ['', [
        Validators.required,
        Validators.pattern(this.urlRegexp),
      ]]
    });
  }

  onSubmit(): void {
    // Retrieve the media from the form fields
    const media = this.getMediaFromForm();

    // POST the media
    this.mediaService.createMedia(media).subscribe(
      () => {
        this.toastr.success('Media successfully created');
        this.router.navigate(['medias']);
      },
      (error: HttpErrorResponse) => this.toastr.error(error.error['reason'], 'Unable to create the media'),
    );
  }

  private getMediaFromForm() {
    // Trim the trailing '/' if any
    let rawMediaUrl: string = this.f.website.value;

    if (rawMediaUrl.endsWith('/')) {
      rawMediaUrl = rawMediaUrl.slice(0, -1);
    }

    // Retrieve the fields from the form
    return new Media(
      new Date(),
      this.f.description.value,
      0,
      this.f.name.value,
      new TrustReport(0),
      new URL(rawMediaUrl));
  }

  get f() {
    return this.createMediaForm.controls;
  }

}
