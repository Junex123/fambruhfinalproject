import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Product, Size } from '../../Class/product';
import { UserService } from '../../service/user.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-productform',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './productform.component.html',
  styleUrl: './productform.component.css'
})
export class ProductformComponent {
  product: Product = new Product();
  file!: File; // Change to single file instead of an array
  isValid!: boolean;
  message!: string;
  mainImage!:any;
  hoverImage!:any;
  detailImage!:any;
  image1!:any;
  image2!:any;
  image3!:any;
  sizes: string[] = Object.values(Size); // Initialize sizes with enum values// Define the available sizes
  allSizes: any[] = []; // Initialize as an empty array
  constructor(private productService: UserService, private router: Router) {}

  onSubmit() {

    const formData = new FormData();
  

    formData.append('product', JSON.stringify(this.product));

    // Append the images
    formData.append('mainImage', this.mainImage);
    formData.append('hoverImage', this.hoverImage);
    formData.append('detailImage', this.detailImage);
    formData.append('image1', this.image1);
    formData.append('image2', this.image2);
    formData.append('image3', this.image3);
  

    // Send the form data to the server
    this.productService.addProduct(formData).subscribe({
      next: (response) => {
        // Handle successful response
        this.isValid = true;
        this.message = "Product added successfully!";
        console.log('productdetails',this.product);
        console.log('sizedetails',this.sizes);
      }, 
      error: (error) => {
        // Handle error response
        console.error('Error adding product:', error);
        this.isValid = false;
        this.message = error.message || 'Something went wrong!';
      }
    });
  }
  

  
  
  

  onChangeMainImage(event: any) {
    this.mainImage = event.target.files[0];
  }

  onChangeHoverImage(event: any) {
    this.hoverImage = event.target.files[0];
  }

  onChangeDetailImage(event: any) {
    this.detailImage = event.target.files[0];
  }

  onChangeImage1(event: any) {
    this.image1 = event.target.files[0];
  }

  onChangeImage2(event: any) {
    this.image2 = event.target.files[0];
  }

  onChangeImage3(event: any) {
    this.image3 = event.target.files[0];
  }

  onClick() {
    this.router.navigate(['/admindashboard']);
  }
}
