## 1) SPRING INTERCEPTORS
### HandlerInterceptor + WebMvcConfigurer

## 2) SPRING WEB FUNDAMENTALS
### THYMELEAF Html + @Controller + Model class + ModelMap class + @ModelAttribute
### th:if + th:each + th:text + th:href + th:action + redirect:/ + forward:/
### HttpServletRequest + @PropertySource + Expression Language @Value #{...}

## 3.1) SPRING WEB FORM
### THYMELEAF Html + @Controller + Model class + @ModelAttribute + @Value + BindingResult
### th:text + th:value + th:if + th:each + th:ref + th:action + th:object + th:field + th:id + th:for
### @SessionAttributes + @SessionAttribute + SessionStatus

* th:field -> it usually gives id, name and value attribute to the input element from form

* ${} -> it allow you to use java code or show the value's variable
* \# -> It is used into ${} to get utility functions, such as #fields.hasErrors, #temporals.format,
        #ids.seq('role') + #ids.prev('role')
* @{} -> It is used as url and routes. It also allows you to call image and css file from resources/static/
* *{} -> It maps the field object


* #ids.seq('someId') -> Normally used in th:id properties for appending a counter to the id attribute value
* #ids.prev('someId') -> If the <label> goes after the element with the #ids.seq(...)
                         function, the "prev" (label goes after "seq") function should be called.

## 3.2) DATA BINDING
### BindingResult + Validator + @InitBinder (addValidators, registerCustomEditor)
### ConstraintValidator + PropertyEditorSupport
### Validating dates (@DateTimeFormat, @Future, @Past)

#### files on src/main/resources
* messages.properties
* static/css/<filename>.css
* templates/<filename>.html



