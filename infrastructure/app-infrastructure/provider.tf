provider "aws" {
  region = var.aws_region
}

terraform {
  backend "s3" {
    bucket         = "your-company-terraform-state"
    key            = "app/terraform.tfstate"
    region         = var.aws_region
    encrypt        = true
    dynamodb_table = "terraform-state-lock"
  }
}