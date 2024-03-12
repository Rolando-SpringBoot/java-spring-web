## 1) SPRING INTERCEPTORS
### HandlerInterceptor + WebMvcConfigurer

## 2) SPRING WEB FUNDAMENTALS
### THYMELEAF Html + @Controller + Model class + ModelMap class + @ModelAttribute
### th:if + th:each + th:text + th:href + th:action + redirect:/ + forward:/
### HttpServletRequest + @PropertySource + Expression Language @Value #{...}

## 3.1) SPRING WEB FORM
### THYMELEAF Html + @Controller + Model class + @ModelAttribute + @Value + BindingResult
### th:text + th:value + th:if + th:ref + th:action + th:object + th:field  + @SessionAttributes
### SessionStatus

* th:field -> it gives a name and value attribute

* ${} -> it allow you to use java code or show the value's variable
* \# -> It is used into ${} to get utility functions, such as #fields.hasErrors, #temporals.format
* @{} -> It is used as url and routes
* *{} -> It maps the field object

## 3.2) DATA BINDING
### BindingResult + Validator + @InitBinder (addValidators, registerCustomEditor)
### ConstraintValidator + PropertyEditorSupport
### Validating dates (@DateTimeFormat, @Future, @Past)

#### files on src/main/resources
* messages.properties
* static/css/<filename>.css
* templates/<filename>.html



