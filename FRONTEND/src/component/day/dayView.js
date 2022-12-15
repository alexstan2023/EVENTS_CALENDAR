import "./dayView.css";

function DayView(props) {
  const cells = [];

  for (let i = 1; i <= 24; i++) {
    cells.push(<td></td>);
  }

  return (
    <tr>
      <td className="left-column">{props.weekday}</td>
      {cells}
    </tr>
  );
}

export default DayView;
