import React from "react";
import { Box } from "@mui/material";

interface IMyprops {
  completed: boolean;
}

export const Completed: React.FC<IMyprops> = (props: IMyprops) => {
  return (
    <>
      {props.completed ? (
        <Box
          sx={{
            borderRadius: "50%",
            backgroundColor: "green",
            width: 15,
            height: 15,
          }}
        ></Box>
      ) : (
        <Box
          sx={{
            borderRadius: "50%",
            backgroundColor: "red",
            width: 15,
            height: 15,
          }}
        ></Box>
      )}
    </>
  );
};
