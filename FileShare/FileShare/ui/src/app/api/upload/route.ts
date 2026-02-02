import { NextResponse } from "next/server";

export async function POST(req: Request) {
  try {
    console.log("✅ /api/upload hit");
    console.log("Content-Type:", req.headers.get("content-type"));

    const formData = await req.formData();
    console.log("✅ formData parsed");

    const backendRes = await fetch("http://localhost:8080/upload", {
      method: "POST",
      body: formData,
    });

    console.log("✅ backend status:", backendRes.status);

    const text = await backendRes.text();
    console.log("✅ backend response:", text);

    return new NextResponse(text, {
      status: backendRes.status,
      headers: {
        "Content-Type": backendRes.headers.get("content-type") ?? "application/json",
      },
    });
  } catch (err) {
    console.error("❌ Upload proxy failed:", err);
    return NextResponse.json({ error: String(err) }, { status: 500 });
  }
}
