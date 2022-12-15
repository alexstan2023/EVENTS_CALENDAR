import { isToday } from "date-fns";
import "./dayView.css";

function DayView(props) {
  const cells = [];

  for (let i = 1; i <= 24; i++) {
    cells.push(<td></td>);
  }

  let isToday;

  if (
    props.day == new Date().getDate() &&
    props.month == new Date().getMonth() &&
    props.year == new Date().getFullYear()
  ) {
    isToday = true;
  } else {
    isToday = false;
  }

  return (
    <tr>
      <td
        className="left-column"
        style={{
          backgroundColor: isToday ? "yellow" : "aqua",
          position: "fixed",
          width: "348px",
        }}
      >
        <b>{props.weekday}</b>
        <br></br>
        <b>
          {props.day}-{props.month}-{props.year}
        </b>
      </td>
      {cells}
    </tr>
  );
}

export default DayView;
