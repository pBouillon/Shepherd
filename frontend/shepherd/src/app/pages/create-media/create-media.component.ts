import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
      name: ['', Validators.required],
    });
  }

  onSubmit(): void {
    alert('submitted');
  }

  get f() {

    return this.createMediaForm.controls;

  }

}
