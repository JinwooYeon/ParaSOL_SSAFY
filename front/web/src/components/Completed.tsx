import React from "react";
import { Box } from "@mui/material";

interface IMyprops {
  completed: boolean;
}

export const Completed: React.FC<IMyprops> = ({ completed }) => {
  return (
    <>
      {/* api 개발 완료 여부: boolean */}
      {completed ? (
        <Box
          sx={{
            borderRadius: "50%",
            backgroundColor: "#53E76C",
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
