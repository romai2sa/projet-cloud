resource "random_pet" "rg_name" {
  prefix = var.resource_group_name_prefix
}

resource "azurerm_resource_group" "rg" {
  name     = random_pet.rg_name.id
  location = var.resource_group_location
}

resource "random_string" "container_name" {
  length  = 25
  lower   = true
  upper   = false
  special = false
}

resource "random_string" "container_name2" {
  length  = 25
  lower   = true
  upper   = false
  special = false
}

resource "azurerm_container_registry" "acr_equipe5_prod" {
  name                = "equipe5registryprod" 
  resource_group_name = azurerm_resource_group.rg.name
  location            = azurerm_resource_group.rg.location
  sku                  = "Basic" 
  admin_enabled = true  
}

resource "azurerm_container_group" "container" {
  name                = "${var.container_group_name_prefix}-${random_string.container_name.result}"
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  ip_address_type     = "Public"
  dns_name_label      = var.dns_name_label
  os_type             = "Linux"
  restart_policy      = var.restart_policy

  image_registry_credential {
    server   = azurerm_container_registry.acr_equipe5_prod.login_server
    username = azurerm_container_registry.acr_equipe5_prod.admin_username
    password = azurerm_container_registry.acr_equipe5_prod.admin_password
  }

  container {
    name   = "${var.container_name_prefix}-${random_string.container_name.result}"
    image  = "${azurerm_container_registry.acr_equipe5_prod.login_server}/back:${var.tag}"
    cpu    = var.cpu_cores
    memory = var.memory_in_gb
    cpu_limit = var.cpu_limit
    memory_limit = var.memory_limit

    environment_variables = {
      FRONTEND_URL =  "http://${var.dns_name_label}.${azurerm_resource_group.rg.location}.azurecontainer.io"
      APPLICATIONINSIGHTS_CONNECTION_STRING = azurerm_application_insights.appli-insight-prod.instrumentation_key
      FRONTEND_PORT = var.port_front
    }

    ports {
      port     = var.port_back
      protocol = "TCP"
    }
  }

  container {
    name   = "${var.container_name_prefix}-${random_string.container_name2.result}"
    image  = "${azurerm_container_registry.acr_equipe5_prod.login_server}/front:${var.tag}"
    cpu    = var.cpu_cores
    memory = var.memory_in_gb
    cpu_limit = var.cpu_limit
    memory_limit = var.memory_limit

    environment_variables = {
      #FRONTEND_URL =  "http://${var.dns_name_label}.${azurerm_resource_group.rg.location}.azurecontainer.io"
      #APPLICATION_INSIGHTS_CONNECTION_STRING = azurerm_application_insights.appli-insight-prod.instrumentation_key
    }

    ports {
      port     = var.port_front
      protocol = "TCP"
    }
  }
}

resource "azurerm_log_analytics_workspace" "log_analytics_workspace_equipe5_prod" {
  name                = "workspace-equipe5-prod"
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  sku                 = "PerGB2018"
  retention_in_days   = 30
}

# Créer la ressource Application Insights
resource "azurerm_application_insights" "appli-insight-prod" {
  name                = "equipe5-prod-app-insights"
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  workspace_id        = azurerm_log_analytics_workspace.log_analytics_workspace_equipe5_prod.id
  application_type    = "web" 
}