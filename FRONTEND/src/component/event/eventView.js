import CalendarView from "../calendar/calendarView";
import { useNavigate } from "react-router-dom";

function EventView() {
  const navigate = useNavigate();

  navigate(
    `/calendar/view/${new Date().getDate()}/${new Date().getMonth()}/${new Date().getFullYear()}`
  );
}

export default EventView;
