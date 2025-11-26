Contact Manager - Day16 Full Project
===================================

How to run in Eclipse (Tomcat 9):
1. Import -> Existing Maven Projects -> select this project's folder.
2. After import, run as -> Run on Server (choose Tomcat 9).
3. The app will be available at http://localhost:8080/contact-manager/ (context path may vary)

Project features:
- List/Add/Edit/Delete contacts (in-memory DAO with sample data)
- JSP views under WEB-INF/views
- ContactServlet (handles GET/POST)
- JSTL included for JSP iteration

Notes:
- Data is stored in-memory; restarting server resets to sample data.
- If you prefer persistence, I can add simple file-based storage or H2 DB.

