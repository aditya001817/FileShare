# **FileShare** — Secure P2P File Sharing (Invite Code Based)

**FileShare is a lightweight peer-to-peer file sharing web application that allows users to upload a file, generate a unique invite code (port), and share that file with others for download using the code.**

### This project is built using:

* Java (HttpServer) backend for upload + download APIs
* Socket-based P2P server for actual file transfer
* Next.js (App Router) frontend with modern UI
* Next.js API Proxy Routes (/api/upload, /api/download) for clean frontend-backend communication


### **Features**

* Upload & Share
* Drag and drop file upload
* File stored temporarily on server machine
* Generates a unique invite code
* Starts a dedicated file server on the generated port

### **Download & Receive**

* Download using invite code
* Fetches file stream from P2P server and downloads in browser

### **Modern Web UI**

* Responsive clean UI
* Upload progress indicator
* Copy invite code button

### **Proxy Architecture (No CORS issues)**

* Frontend sends request to Next.js routes (localhost:3000/api/...)
* Next.js proxies the request to Java backend (localhost:8080/...)

### **How It Works (Architecture)**

#### 🔹 Upload Flow

* User uploads a file in the UI
* UI sends POST /api/upload (Next.js route)
* Next.js proxies it → Java backend POST /upload
* Java backend:
*      1. parses multipart file
*      2. stores file in temp directory
*      3. generates a port code (invite code)
*      4. starts a socket file server on that port
* UI displays the invite code

#### **🔹 Download Flow**

* User enters invite code
* UI sends GET /api/download/{code}
* Next.js proxies it → Java backend GET /download/{code}
* Java backend connects to file server (localhost:{code})
* File is streamed back to the browser for download

### **Tech Stack**

#### **Frontend**

* Next.js 14 (App Router)
* React
* Tailwind CSS
* Axios
* React Dropzone

#### **Backend**

* Java 17
* com.sun.net.httpserver.HttpServer
* Socket Programming
* Apache Commons IO
* Apache Commons FileUpload


### **Prerequisites**

Make sure you have installed:
* Java 17+
* Maven
* Node.js 18+
* npm


### **Setup & Run Locally**
**1) Run Backend (Java)** 

Go to the backend directory (where pom.xml exists):

            cd C:\FileShare\FileShare

Build and run:

            mvn clean compile exec:java "-Dexec.mainClass=p2p.App"

Backend will start on:
 http://localhost:8080

**2) Run Frontend (Next.js)**

Go to UI folder:

           cd ui

Install dependencies:

           npm install

Start development server:

           npm run dev

Frontend will start on:
 http://localhost:3000


### **API Endpoints**

#### Upload File

POST /upload (backend)
POST /api/upload (proxy route)
*  Request: multipart/form-data
*  Field: file


#### **Download** File

* GET /download/{port} (backend)
* GET /api/download/{port} (proxy route)

**Response**:

* File binary stream (application/octet-stream)
* Content-Disposition header for filename

#### **Security Notes**

This project is designed for local / lab / academic use.
Recommended improvements for production:
* Invite code expiry (TTL)
* Authentication / authorization
* Rate limiting
* Encryption for file transfer
* Cloud-based storage / streaming
* 
#### **Example Usage**

1. Open http://localhost:3000
2. Upload file → Copy invite code
3. Share invite code with another user
4. Receiver enters code → downloads file