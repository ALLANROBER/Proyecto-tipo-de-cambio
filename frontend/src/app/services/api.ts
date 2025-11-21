import { MessageDto } from "../dto/MessageDto";

const API_BASE = "http://localhost:8080/api/tipo-cambio";

export async function consultarTipoCambio(): Promise<MessageDto<any>> {
  const res = await fetch(`${API_BASE}/consultar`);
  return await res.json();
}

export async function obtenerHistorial(): Promise<MessageDto<any[]>> {
  const res = await fetch(`${API_BASE}/historial`);
  return await res.json();
}
