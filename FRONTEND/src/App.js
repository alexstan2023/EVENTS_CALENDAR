import "./App.css";
import { Routes, Route } from "react-router-dom";
import CalendarView from "./component/calendar/calendarView";

function App() {
  return (
    <Routes>
      <Route path="/calendar/view" element={<CalendarView />}></Route>
    </Routes>
  );
}

export default App;
