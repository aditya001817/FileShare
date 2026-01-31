FileShare - P2P File Sharing Application
FileShare is a peer-to-peer file sharing application that allows users to share files directly between devices using a simple invite code system.

**Project Structure**
    src/main/java/p2p: Java backend code
    App.java: Main application entry point
    controller/: API controllers
    service/: Business logic services
    utils/: Utility classes
    ui/: Next.js frontend application
    src/app: Next.js app router pages
    src/components: React components
**_Features_**
    Drag and drop file upload
    File sharing via invite codes (port numbers)
    File downloading using invite codes
    Modern, responsive UI
    Direct peer-to-peer file transfer
    Prerequisites
    Java 11+ (for the backend)
    Node.js 18+ and npm (for the frontend)
    Maven (for building the Java project)

**Manual Setup \n
    Backend Setup** \n
        Build the Java project:
        
            mvn clean package
            Run the backend server:
            
            java -jar target/p2p-1.0-SNAPSHOT.jar
            The backend server will start on port 8080.

**Frontend Setup \n
    Install dependencies:**
    
        cd ui
        npm install
        Run the development server:
        
        npm run dev
        The frontend will be available at http://localhost:3000.

**How It Works \n
    File Upload:**
    
        User uploads a file through the UI
        The file is sent to the Java backend
        The backend assigns a unique port number (invite code)
        The backend starts a file server on that port
        File Sharing:
        
        The user shares the invite code with another user
        The other user enters the invite code in their UI
        File Download:
        
        The UI connects to the specified port
        The file is transferred directly from the host to the recipient
        Architecture
                    ┌─────────────┐      ┌─────────────┐      ┌─────────────┐
                    │             │      │             │      │             │
                    │  Next.js UI │◄────►│ Java Server │◄────►│ Peer Device │
                    │             │      │             │      │             │
                    └─────────────┘      └─────────────┘      └─────────────┘
                    Low Level Design (LLD)

**Component Details**

    **Frontend Components**
    
    NextJSApp: Main application component managing state and routing
    FileUploadComponent: Handles drag-and-drop file uploads
    FileDownloadComponent: Manages file downloads using invite codes

    **Backend Components**
    
    App: Main application entry point and server initialization
    FileController: REST API endpoints for file operations
    FileService: Core business logic for file handling
    FileUtils: Utility functions for file validation and port management
    Data Flow
    
    File uploads are handled through drag-and-drop
    Invite codes (port numbers) are generated for sharing
    Direct peer-to-peer file transfer using WebSocket connections
    Security Considerations
    This is a demo application and does not include encryption or authentication
    For production use, consider adding:
    File encryption
    User authentication
    HTTPS support
    Port validation and security