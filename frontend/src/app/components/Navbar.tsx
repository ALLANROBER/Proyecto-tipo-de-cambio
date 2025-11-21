"use client";

import Link from "next/link";

export default function Navbar() {
  return (
    <nav className="navbar">
      <Link href="/">Inicio</Link>
      <Link href="/historial">Historial</Link>
    </nav>
  );
}
