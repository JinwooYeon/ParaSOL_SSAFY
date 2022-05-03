import styled from "styled-components";

interface ITest {
  method: string;
}

export const ApiMethod = styled.div`
  width: 65px;
  color: white;
  padding: 4px 10px;
  font-size: 13;
  border-radius: 10px;
  font-weight: 700;
  background-color: ${(props: ITest) => {
    switch (props.method) {
      case "GET":
        return "#5BA4E7";
      case "POST":
        return "#26CE7D";
      case "PATCH":
        return "#EB9A29";
      case "DELETE":
        return "red";
      case "LEAST":
        return "black";
      default:
        return "black";
    }
  }};
`;
