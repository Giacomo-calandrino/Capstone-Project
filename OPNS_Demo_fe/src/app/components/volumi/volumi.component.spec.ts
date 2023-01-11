import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VolumiComponent } from './volumi.component';

describe('VolumiComponent', () => {
  let component: VolumiComponent;
  let fixture: ComponentFixture<VolumiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VolumiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VolumiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
