import { createBrowserRouter } from 'react-router-dom';
import App from '../App';
import LiveChartPage from '../pages/LiveChart/LiveChartPage';

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      { path: '/', element: <LiveChartPage /> }
    ]
  }
]);

export default router;
