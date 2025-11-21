export interface MessageDto<T = any> {
  success: boolean;
  message: string;
  data?: T;
}
