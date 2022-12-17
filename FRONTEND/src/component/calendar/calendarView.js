import DayView from "../day/dayView";
import "./calendarView.css";
import { useNavigate, useProps, useParams } from "react-router-dom";
import Day from "../../utils/day";

function CalendarView() {
  const { day, month, year } = useParams();
  const navigate = useNavigate();

  const previousDay = Day.getPreviousDay(new Date(year, month, day), 1);
  const nextDay = Day.getPreviousDay(new Date(year, month, day), -1);

  const hour = [];
  const days = [];

  hour.push(
    <th
      className="top-left-corner"
      style={{ position: "fixed", width: "348px" }}
    >
      <img
        src="https://cdn-icons-png.flaticon.com/512/892/892554.png"
        onClick={() => {
          navigate(
            `/calendar/view/${previousDay.getDate()}/${previousDay.getMonth()}/${previousDay.getFullYear()}`
          );
        }}
      ></img>
      <img
        src="https://www.pngfind.com/pngs/m/89-899453_arrow-pointing-down-png-arrow-pointing-down-icon.png"
        onClick={() => {
          navigate(
            `/calendar/view/${nextDay.getDate()}/${nextDay.getMonth()}/${nextDay.getFullYear()}`
          );
        }}
      ></img>
    </th>
  );

  hour.push(<th className="top-row">12AM</th>);
  for (let i = 1; i <= 11; i++) hour.push(<th className="top-row">{i}AM</th>);
  hour.push(<th className="top-row">12PM</th>);
  for (let i = 1; i <= 11; i++) {
    hour.push(<th className="top-row">{i}PM</th>);
  }

  for (let i = 1; i <= 7; i++) {
    days.push(
      <DayView
        weekday={Day.transformWeekDay(
          Day.getPreviousDay(new Date(year, month, day), 4 - i).getDay()
        )}
        day={Day.getPreviousDay(new Date(year, month, day), 4 - i).getDate()}
        month={Day.getPreviousDay(new Date(year, month, day), 4 - i).getMonth()}
        year={Day.getPreviousDay(
          new Date(year, month, day),
          4 - i
        ).getFullYear()}
      ></DayView>
    );
  }

  return (
    <table>
      <tr>{hour}</tr>
      {days}
    </table>
  );
}

export default CalendarView;
