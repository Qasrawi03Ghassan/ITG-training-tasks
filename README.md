# Task 3: Building a dynamic configuration management system using Java  
### Scenario  
You’re building a **configuration management system** for a cloud-based application.  
This system stores and manages parameters (settings) that control application behavior — things like SMS limits, retry counts, URLs, etc.  
Each configuration parameter:  
- Has a name, type, and default value.
- Can have multiple versions (e.g., Dev, QA, Prod environments).
- Can be temporarily overridden by a user or service.
----------------------------------------------------------------  
Task:  
Write a Java application that can:  
1. **Create, update, and delete configuration parameters.**  
      Each parameter has:
      id: Long  
      name: String  
      type: String ("STRING", "NUMBER", "BOOLEAN")  
      defaultValue: Object  
      environment: String ("DEV", "QA", "PROD")  
      overriddenValue: Object (nullable)  
      lastUpdated: LocalDateTime
3. **Support dynamic overrides:**
   - If an overridden value exists → use it.  
   - If not → use the default value.
4. **Track version history** (every time a parameter changes, store the old version with timestamp).
5. **Provide a search feature:**  
    - Search by environment, type, or partial name (case-insensitive).  
6. **Export configuration to JSON** (pretty format) for deployment.
7. **Validate input types** before saving (e.g., “NUMBER” must contain numeric value).
---------------------------------------------------------------------
### Example usage  
```bash
  > addParameter("sms.limit", "NUMBER", 100, "PROD")  
  > addParameter("feature.toggle", "BOOLEAN", true, "DEV")
  > overrideValue("sms.limit", "PROD", 200)  
  > updateParameter("feature.toggle", "BOOLEAN", false, "DEV")  
  > search("sms")
  > export("PROD")
```
---------------------------------------------------------------------
### Expected output example  
```json
{"environment": "PROD","parameters": [{"name": "sms.limit","type": "NUMBER","value": 200,"defaultValue": 100}] }
```

