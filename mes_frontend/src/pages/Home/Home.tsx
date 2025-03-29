import { useEffect } from 'react';

export default function Home() {
  useEffect(() => {
    document.title = '홈 | MES 시스템';
  }, []);

  return (
    <div className="min-h-screen bg-gray-100 flex flex-col items-center justify-center px-4">
      <h1 className="text-4xl font-bold text-blue-600 mb-4">MES 시스템 홈</h1>
      <p className="text-gray-700 text-lg text-center max-w-md">
        공장 운영을 위한 MES 시스템에 오신 것을 환영합니다. 좌측 메뉴 또는 상단
        메뉴에서 기능을 선택하세요.
      </p>
    </div>
  );
}
