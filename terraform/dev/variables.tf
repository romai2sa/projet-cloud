variable "resource_group_location" {
  type        = string
  default     = "eastus"
  description = "Location for all resources."
}

variable "resource_group_name_prefix" {
  type        = string
  default     = "rg"
  description = "Prefix of the resource group name that's combined with a random value so name is unique in your Azure subscription."
}

variable "environment" {
  type        = string
  default     = "dev"
  description = "Workspace"
}

variable "container_name_prefix" {
  type        = string
  description = "Prefix of the container name that's combined with a random value so name is unique in your Azure subscription."
  default     = "aci"
}

variable "port_front" {
  type        = number
  description = "Port to open on the container and the public IP address."
  default     = 9090
}

variable "port_back" {
  type        = number
  description = "Port to open on the container and the public IP address."
  default     = 8080
}

variable "subscription_id" {
  type        = string
  description = "Azure Subscription ID"
}

variable "cpu_cores" {
  type        = number
  description = "The number of CPU cores to allocate to the container."
  default     = 1
}

variable "memory_in_gb" {
  type        = number
  description = "The amount of memory to allocate to the container in gigabytes."
  default     = 2
}

variable "memory_limit" {
  type        = number
  description = "The maximum amount of memory to allocate to the container in gigabytes."
  default     = 3
}

variable "cpu_limit" {
  type        = number
  description = "The maximum number of CPU cores to allocate to the container."
  default     = 2
}

variable "restart_policy" {
  type        = string
  description = "The behavior of Azure runtime if container has stopped."
  default     = "Always"
  validation {
    condition     = contains(["Always", "Never", "OnFailure"], var.restart_policy)
    error_message = "The restart_policy must be one of the following: Always, Never, OnFailure."
  }
}


variable "container_group_name_prefix" {
  description = "Prefix for the container group names in different environments"
  type        = string
  default =  "dev-container"
}

variable "dns_name_label" {
  description = "DNS name labels for different environments"
  type        = string
  default = "equipe5-dev"
}

variable "tag" {
  type        = string
  description = "Le numéro de version de l'image "
}

