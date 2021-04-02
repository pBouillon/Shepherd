import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Media } from 'src/app/models/medias/media';

@Component({
  selector: 'app-create-media',
  templateUrl: './create-media.component.html',
  styleUrls: ['./create-media.component.css']
})
export class CreateMediaComponent implements OnInit {

  readonly urlRegexp = 'https://([\\da-z.-]{1,64})\\.([a-z.]{2,6})[/\\w .-]{0,256}/?';

  public createMediaForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
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
    alert('submitted');
  }

  get f() {
    return this.createMediaForm.controls;
  }

}
