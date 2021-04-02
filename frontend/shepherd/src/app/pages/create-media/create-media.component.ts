import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Media } from 'src/app/models/medias/media';

@Component({
  selector: 'app-create-media',
  templateUrl: './create-media.component.html',
  styleUrls: ['./create-media.component.css']
})
export class CreateMediaComponent implements OnInit {

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
    });
  }

  onSubmit(): void {
    alert('submitted');
  }

  get f() {

    return this.createMediaForm.controls;

  }

}
