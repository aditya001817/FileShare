📁 FileShare — Secure P2P File Sharing (Invite Code Based)
<p align="center"> <img src="https://img.shields.io/badge/Java-17+-red?style=for-the-badge&logo=openjdk&logoColor=white" /> <img src="https://img.shields.io/badge/Maven-Build-blue?style=for-the-badge&logo=apachemaven&logoColor=white" /> <img src="https://img.shields.io/badge/Next.js-14-black?style=for-the-badge&logo=nextdotjs&logoColor=white" /> <img src="https://img.shields.io/badge/TailwindCSS-UI-38B2AC?style=for-the-badge&logo=tailwindcss&logoColor=white" /> </p>

FileShare is a lightweight peer-to-peer file sharing web application that allows users to upload a file, generate a unique invite code (port), and share that file with others for download using the code.

✅ Built with a Java HttpServer backend, socket-based P2P file transfer, and a modern Next.js + Tailwind UI.

🚀 Features
✅ Upload & Share

Drag and drop file upload

Stores uploaded file in a temporary directory

Generates a unique invite code (port)

Starts a dedicated file server on the generated port

✅ Download & Receive

Download using invite code

Streams file to browser as downloadable attachment

✅ Modern UI

Responsive design

Upload progress state

Copy invite code button

✅ Proxy Setup (No CORS issues)

UI communicates via Next.js proxy routes:

/api/upload

/api/download/{port}

🧠 How It Works (Architecture)
🔹 Upload Flow

User uploads file in UI

UI sends POST /api/upload

Next.js proxies request → Java backend POST /upload

Java backend:

parses multipart data

stores file in temp directory

generates invite code (valid port)

starts socket-based file server

UI shows invite code to share

🔹 Download Flow

User enters invite code

UI sends GET /api/download/{port}

Next.js proxies request → Java backend GET /download/{port}

Java backend connects to P2P server on that port

File is returned as application/octet-stream

🛠️ Tech Stack
Frontend

Next.js 14 (App Router)

React

Tailwind CSS

Axios

React Dropzone

Backend

Java 17

com.sun.net.httpserver.HttpServer

Socket Programming

Apache Commons IO

Apache Commons FileUpload

📂 Project Structure
FileShare/
├── src/main/java/p2p/
│   ├── App.java
│   ├── controller/
│   │   └── FileController.java
│   ├── service/
│   │   └── FileSharer.java
│   └── utils/
│       └── UploadUtils.java
│
└── ui/
    ├── src/app/
    │   ├── page.tsx
    │   ├── layout.tsx
    │   └── api/
    │       ├── upload/route.ts
    │       └── download/[port]/route.ts
    └── src/components/
        ├── FileUpload.tsx
        ├── FileDownload.tsx
        └── InviteCode.tsx

✅ Prerequisites

Make sure you have installed:

Java 17+

Maven

Node.js 18+

npm

⚙️ Setup & Run Locally
✅ 1) Run Backend (Java)

Go to your backend folder (where pom.xml exists):

cd C:\FileShare\FileShare


Run backend:

mvn clean compile exec:java "-Dexec.mainClass=p2p.App"


Backend runs on:

✅ http://localhost:8080

✅ 2) Run Frontend (Next.js)

Go to UI folder:

cd ui


Install dependencies:

npm install


Start UI:

npm run dev


Frontend runs on:

✅ http://localhost:3000

🔌 API Endpoints
✅ Upload File

POST /upload (Java backend)
POST /api/upload (Next.js proxy route)

Request: multipart/form-data
Field: file

Response example:

{ "port": 53694 }

✅ Download File

GET /download/{port} (Java backend)
GET /api/download/{port} (Next.js proxy route)

Response:

File stream: application/octet-stream

Header: Content-Disposition: attachment; filename="..."

✅ Example Usage

Open http://localhost:3000

Upload any file

Copy invite code

Enter invite code in Receive a File tab

Download file successfully ✅

📌 Important Notes

Invite codes are valid port numbers

Valid port range: ✅ 1 – 65535

Recommended safe dynamic range: ✅ 49153 – 65535

🧪 Common Issues & Fixes
❌ Upload fails

✅ Make sure frontend does NOT manually set:

"Content-Type": "multipart/form-data"


Axios automatically sets correct boundary with FormData.

❌ Invalid invite code

If code is greater than 65535, update port generator in UploadUtils.

🚀 Future Enhancements (Optional)

Invite code expiry (TTL)

Single-use / limited downloads per code

Download progress bar

Multi-device LAN sharing

Authentication support

👨‍💻 Author

Aditya Chauhan
Built as a peer-to-peer file sharing project using Java + Next.js.
