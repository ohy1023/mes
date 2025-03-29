// routes/PrivateRoute.tsx
import { Navigate } from 'react-router-dom';

export default function PrivateRoute({ children }: { children: JSX.Element }) {
  const isAuthenticated = true; // 예시: 실제로는 auth context 등에서 받아옴
  return isAuthenticated ? children : <Navigate to="/" />;
}
